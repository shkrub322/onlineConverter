package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllByBankAndCityId(Long bankId, Long cityId);

    List<Department> getAllByBank(Long id);

    void save(List<Department> departments);
}
