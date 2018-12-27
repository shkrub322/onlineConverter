package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.Rate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RateRepository extends CrudRepository<Rate, Long> {
    List<Rate> findAllByDepartmentId(Long id);

    List<Rate> findAllByNameAndOperation(String name, String operation);
}
