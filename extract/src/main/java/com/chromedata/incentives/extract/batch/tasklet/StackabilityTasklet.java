package com.chromedata.incentives.extract.batch.tasklet;

import com.chromedata.incentives.extract.batch.component.CommonComponent;
import com.chromedata.incentives.extract.dao.model.BatchDataSet;
import com.chromedata.incentives.extract.dao.model.SignatureStackability;
import com.chromedata.incentives.extract.presentation.CSVDataSetWriter;
import com.chromedata.incentives.extract.presentation.PresentationMarshaller;
import com.chromedata.incentives.extract.presentation.PresentationMarshallerFactory;
import com.chromedata.incentives.extract.presentation.model.CSVDataSet;
import com.chromedata.incentives.extract.presentation.model.Stackability;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import static com.chromedata.incentives.extract.batch.component.ExtractConstants.CONSUMER_RESPONSE_DIR;
import static com.chromedata.incentives.extract.batch.component.ExtractConstants.DEALER_RESPONSE_DIR;
import static com.chromedata.incentives.extract.batch.component.ExtractConstants.EXIT_STATUS_FAIL;
import static com.chromedata.incentives.extract.batch.component.ExtractConstants.REQUESTED_LOCALE;
import static com.chromedata.incentives.extract.batch.component.ExtractConstants.STACKABILITY_CONSUMER_COUNT;
import static com.chromedata.incentives.extract.batch.component.ExtractConstants.STACKABILITY_DEALER_COUNT;

@Component("stackabilityTasklet")
public class StackabilityTasklet implements Tasklet {

    private static final Logger LOG = LogManager.getLogger(StackabilityTasklet.class);

    private final CSVDataSetWriter<Stackability> csvWriterToDealer;
    private final CSVDataSetWriter<Stackability> csvWriterToConsumer;

    @Autowired
    private PresentationMarshallerFactory marshallerFactory;

    @Autowired
    private CommonComponent commonComponent;

    public StackabilityTasklet() {
        this.csvWriterToDealer = new CSVDataSetWriter<>(Stackability.class);
        this.csvWriterToConsumer = new CSVDataSetWriter<>(Stackability.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public RepeatStatus execute(final StepContribution contribution, final ChunkContext chunkContext) throws Exception {
        try {
            ExecutionContext       jobExecutionContext    = chunkContext.getStepContext().getStepExecution().getJobExecution().getExecutionContext();
            PresentationMarshaller presentationMarshaller = marshallerFactory.create((Locale) jobExecutionContext.get(REQUESTED_LOCALE));

            Set<SignatureStackability> stackabilities      = commonComponent.getCachedStackabilitiesToDealer();
            Set<Integer>               signatureHistoryIds = commonComponent.getCachedSignatureHistoryIdsToDealer();
            Set<Integer>               signatureIds        = commonComponent.getCachedSignatureIdsToDealer();

            writeStackability(jobExecutionContext, presentationMarshaller, stackabilities, signatureHistoryIds, signatureIds, DEALER_RESPONSE_DIR);
            presentationMarshaller.resetData();

            stackabilities = commonComponent.getCachedStackabilitiesToConsumer();
            signatureHistoryIds = commonComponent.getCachedSignatureHistoryIdsToConsumer();
            signatureIds = commonComponent.getCachedSignatureIdsToConsumer();
            writeStackability(jobExecutionContext, presentationMarshaller, stackabilities, signatureHistoryIds, signatureIds, CONSUMER_RESPONSE_DIR);
            presentationMarshaller.resetData();

            return RepeatStatus.FINISHED;
        } catch (Exception e) {
            LOG.error("Error...", e);
            CommonComponent.updateJobExecutionCompleteStatus(chunkContext.getStepContext().getStepExecution().getJobExecution(), EXIT_STATUS_FAIL);
            throw e;
        }
    }

    private void writeStackability(final ExecutionContext jobExecutionContext,
                                   final PresentationMarshaller presentationMarshaller,
                                   final Set<SignatureStackability> stackabilities,
                                   final Set<Integer> signatureHistoryIds,
                                   final Set<Integer> signatureIds,
                                   final String type) throws java.io.IOException {

        LOG.debug("Total Cached stackabilities count for {} - {}", type, stackabilities.size());
        stackabilities.removeIf(stackability -> !signatureHistoryIds.contains(stackability.getFromSignatureHistoryId())
                                                || !signatureIds.contains(stackability.getToSignatureId()));


        final CSVDataSetWriter<Stackability> csvWriter = DEALER_RESPONSE_DIR.equals(type) ? csvWriterToDealer : csvWriterToConsumer;
        try {
            File responseFileXtl = (File) jobExecutionContext.get(type);
            LOG.debug("Started Writing stackabilities for {} - {}", type,  stackabilities.size());

            BatchDataSet dataSet = new BatchDataSet();
            dataSet.setStackabilityList(new ArrayList<>(stackabilities));
            dataSet.getStackabilityList().forEach(presentationMarshaller::addStackability);

            CSVDataSet data = presentationMarshaller.getData();
            csvWriter.openFile(responseFileXtl);
            csvWriter.writeCollection(data.getStackabilityList());

            if(DEALER_RESPONSE_DIR.equals(type)) {
                STACKABILITY_DEALER_COUNT.getAndAdd(data.getStackabilityList().size());
            }
            else {
                STACKABILITY_CONSUMER_COUNT.getAndAdd(data.getStackabilityList().size());
            }

            LOG.debug("Wrote stackabilities for {} - {}", type,  stackabilities.size());
        } finally {
            csvWriter.closeFile();
        }
    }
}
