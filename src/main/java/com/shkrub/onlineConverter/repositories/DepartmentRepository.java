package com.shkrub.onlineConverter.repositories;

import com.shkrub.onlineConverter.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<Department, Long> {
    List<Department> findAllByBankId(Long id);
}
