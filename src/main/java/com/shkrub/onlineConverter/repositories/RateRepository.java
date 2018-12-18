package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.Rate;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RateRepository extends CrudRepository<Rate, Long> {
    List<Rate> findAllByDepartmentId(Long id);
    List<Rate> findAllByNameAndOperation(String name, String operation);
}
