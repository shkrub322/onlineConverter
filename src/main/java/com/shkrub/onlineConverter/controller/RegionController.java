package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Rate;
import com.shkrub.onlineConverter.entities.Region;
import com.shkrub.onlineConverter.service.RegionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("regions")
public class RegionController {
    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public List<Region> getAllRegions(){
        return regionService.getAll();
    }
}
