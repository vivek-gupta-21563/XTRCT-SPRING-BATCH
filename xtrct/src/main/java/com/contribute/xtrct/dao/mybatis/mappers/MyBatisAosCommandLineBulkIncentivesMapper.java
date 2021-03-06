package com.contribute.xtrct.dao.mybatis.mappers;

import com.contribute.xtrct.dao.model.BatchGeography;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MyBatisAosCommandLineBulkIncentivesMapper {

    List<Integer> getVehicleStyles(@Param("acode") String acode);

    List<BatchGeography> getGeographies(@Param("languageCode") String languageCode,
                                        @Param("countryCode") String countryCode,
                                        @Param("geoUID") final Integer geographyId);
}
