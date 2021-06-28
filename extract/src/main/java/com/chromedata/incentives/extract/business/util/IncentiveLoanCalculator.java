package com.chromedata.incentives.extract.business.util;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.beans.factory.annotation.Qualifier;import org.springframework.context.MessageSource;import org.springframework.stereotype.Component;import java.math.BigDecimal;import java.math.RoundingMode;import java.text.NumberFormat;import java.util.Locale;/** * Helper class to make calculations related to loan terms and disclosure statements */@Componentpublic class IncentiveLoanCalculator {    private static final BigDecimal ONE_THOUSAND = BigDecimal.valueOf(1000);    private static final BigDecimal ONE = BigDecimal.ONE;    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);    private static final BigDecimal MONTHS_PER_YEAR = new BigDecimal(12);    private static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;    private final MessageSource extractMessages;    @Autowired    public IncentiveLoanCalculator(@Qualifier("extractMessageSource") MessageSource extractMessages) {        this.extractMessages = extractMessages;    }    /**     * Calculates and returns the financial disclosure statement for given apr and term range     * <br/><br/>     * This disclosure statement is per $1,000 financed     *     * @param apr the annual percentage rate (i.e. 3.9, not 0.039)     * @param termFrom lowest loan term in months     * @param termTo   highest loan term in months     * @return String disclosure statement     */    public String generateFinancialDisclosure(BigDecimal apr, int termFrom, int termTo, Locale locale) {        String disclosure = null;        if (apr != null) {            final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);            BigDecimal monthlyPaymentRangeLow = calculateMonthlyLoanPayment(ONE_THOUSAND, apr, termFrom);            String amount = currencyFormat.format(monthlyPaymentRangeLow);            if (termFrom != termTo) {                BigDecimal monthlyPaymentRangeHigh = calculateMonthlyLoanPayment(ONE_THOUSAND, apr, termTo);                amount = String.format("%s - %s", currencyFormat.format(monthlyPaymentRangeLow), currencyFormat.format(monthlyPaymentRangeHigh));            }            disclosure = extractMessages.getMessage("financial.disclosure", new Object[]{amount}, locale);        }        return disclosure;    }    /**     * Using the terms of the loan, calculate the monthly payment using the following formula:     *      A = (i)(P)(1+i)ⁿ/(1+i)ⁿ-1     *  where:     *      A = the monthly payment amount     *      P = principal (loan amount)     *      i = interest rate per month (yearly rate / 12)     *      n = number of months in term     *     * @param loanAmount     the amount of the loan     * @param apr            the annual percentage rate (i.e. 3.9, not 0.039)     * @param numberOfMonths the number of months in the term of the loan     * @return the monthly payment amount     */    public BigDecimal calculateMonthlyLoanPayment(BigDecimal loanAmount, BigDecimal apr, int numberOfMonths){        if (loanAmount == null || BigDecimal.ZERO.equals(loanAmount)) {            return BigDecimal.ZERO;        } else if (numberOfMonths == 0) {            return loanAmount;        } else if (BigDecimal.ZERO.compareTo(apr) == 0) {            return loanAmount.divide(new BigDecimal(numberOfMonths), ROUNDING_MODE);        }        BigDecimal termInMonths = new BigDecimal(numberOfMonths);        BigDecimal monthlyRate = apr.divide(ONE_HUNDRED).divide(MONTHS_PER_YEAR, 100, ROUNDING_MODE);        BigDecimal monthlyRatePlusOne = monthlyRate.add(ONE);        BigDecimal monthlyRatePlusOneToPowerOfTerm = monthlyRatePlusOne.pow(termInMonths.intValue());        return monthlyRate                .multiply(loanAmount)                .multiply(monthlyRatePlusOneToPowerOfTerm)                .divide(monthlyRatePlusOneToPowerOfTerm.subtract(ONE), ROUNDING_MODE);    }}