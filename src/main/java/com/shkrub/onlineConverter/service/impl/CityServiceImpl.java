package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.City;
import com.shkrub.onlineConverter.repositories.CityRepository;
import com.shkrub.onlineConverter.service.CityService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    @Transactional
    public List<City> getMain() {
        String[] mainCities = {"Минск", "Гомель", "Могилев", "Витебск",
                "Брест", "Гродно"};
        List<City> cities = (List<City>) cityRepository.findAll();
        List<City> resultCities = new ArrayList<>();
        for(City city : cities){
            for (String cityName : mainCities){
                if (city.equals(cityName)){
                    resultCities.add(city);
                    break;
                }
            }
        }
        return resultCities;
    }

    @Override
    @Transactional
    public void save(List<City> cities) {
        cityRepository.deleteAll();
        cityRepository.saveAll(cities);
    }
}
