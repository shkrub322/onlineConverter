package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Rate;

import java.util.List;

public interface RateService {
    List<Rate> getAllByDepartmentId(Long id);
    List<Rate> getProfitableValue(String from, String to);
    List<Rate> getProfitableValueByRegionId(String from, String to, Long regionId);
    List<Rate> getProfitableValueByCityId(String from, String to, Long cityId);
    void updateAll(List<Rate> newRates);
}
