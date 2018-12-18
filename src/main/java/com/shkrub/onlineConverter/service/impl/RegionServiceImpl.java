package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Region;
import com.shkrub.onlineConverter.repositories.RegionRepository;
import com.shkrub.onlineConverter.service.RegionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {
    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    @Transactional
    public List<Region> getAll() {
        List<Region> regions = (List<Region>) regionRepository.findAll();
        return regions;
    }

    @Override
    public void updateAll(List<Region> newRegions) {
        for(Region region: newRegions){
            regionRepository.save(region);
        }
    }
}
