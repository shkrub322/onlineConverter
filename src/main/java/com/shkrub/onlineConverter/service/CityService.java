package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.City;

import java.util.List;

public interface CityService {
    List<City> getByRegionId(Long id);

    List<City> getMain();

    void save(List<City> cities);
}
