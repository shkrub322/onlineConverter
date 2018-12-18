package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
    List<City> findAllByRegionId(Long id);
}
