package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Department;

import java.util.List;

public interface DepartmentService{
    List<Department> getAllByBankAndCityId(Long bankId, Long cityId);
    List<Department> getAllByBankAndRegionId(Long bankId, Long regionId);
    List<Department> getAllByBank(Long id);
    void updateAll(List<Department> newDepartments);
}
