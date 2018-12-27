package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Region;
import com.shkrub.onlineConverter.repositories.RegionRepository;
import com.shkrub.onlineConverter.service.RegionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
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
        return (List<Region>) regionRepository.findAll();
    }

    @Override
    @Transactional
    public void save(List<Region> regions) {
        regionRepository.deleteAll();
        regionRepository.saveAll(regions);
    }
}
