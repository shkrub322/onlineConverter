package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Department;
import com.shkrub.onlineConverter.repositories.DepartmentRepository;
import com.shkrub.onlineConverter.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }


    @Override
    public List<Department> getAllByBankAndCityId(Long bankId, Long cityId) {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        List<Department> resultDep = new ArrayList<>();
        for (Department department : departments) {
            if (department.getBank().getId().equals(bankId) && department.getCity().getId().equals(cityId)){
                resultDep.add(department);
            }
        }
        return resultDep;
    }

    @Override
    public List<Department> getAllByBankAndRegionId(Long bankId, Long regionId) {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        List<Department> resultDep = new ArrayList<>();
        for (Department department : departments){
            if (department.getBank().getId().equals(bankId) &&
                    department.getCity().getRegion().getId().equals(regionId)){
                resultDep.add(department);
            }
        }
        return resultDep;
    }

    @Override
    public List<Department> getAllByBank(Long id) {
        return departmentRepository.findAllByBankId(id);
    }

    @Override
    public void updateAll(List<Department> newDepartments) {
        for (Department department : newDepartments){
            departmentRepository.save(department);
        }
    }
}
