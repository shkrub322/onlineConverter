package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.City;
import com.shkrub.onlineConverter.repositories.CityRepository;
import com.shkrub.onlineConverter.service.CityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepositor) {
        this.cityRepository = cityRepositor;
    }

    @Override
    @Transactional
    public List<City> getByRegionId(Long id) {
        return cityRepository.findAllByRegionId(id);
    }

    @Override
    public void updateAll(List<City> newCities) {
        for(City city : newCities){
            cityRepository.save(city);
        }
    }
}
