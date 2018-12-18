package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.City;

import java.util.List;

public interface CityService {
    List<City> getByRegionId(Long id);
    void updateAll(List<City> newCities);
}
