package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Rate;

import java.util.List;

public interface RateService {
    List<Rate> getAllByDepartmentId(Long id);

    Double getProfitableValue(String from, String to);

    Double getProfitableValueByCityId(String from, String to, Long id);

    void updateAll(List<Rate> newRates);

    void save(List<Rate> rates);
}
