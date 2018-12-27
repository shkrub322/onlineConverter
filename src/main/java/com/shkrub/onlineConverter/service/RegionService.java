package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Region;

import java.util.List;

public interface RegionService {
    List<Region> getAll();

    void save(List<Region> regions);
}
