package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.City;
import com.shkrub.onlineConverter.service.CityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cities")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("{id}")
    public List<City> getAllCitiesByRegion(@PathVariable(name = "id") Long id){
        return cityService.getByRegionId(id);
    }
}
