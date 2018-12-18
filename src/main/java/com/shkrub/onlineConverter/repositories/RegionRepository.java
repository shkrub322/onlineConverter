package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.Region;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends CrudRepository<Region, Long> {
}
