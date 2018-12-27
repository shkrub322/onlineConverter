package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Department;
import com.shkrub.onlineConverter.repositories.DepartmentRepository;
import com.shkrub.onlineConverter.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public List<Department> getAllByBank(Long id) {
        return departmentRepository.findAllByBankId(id);
    }

    @Override
    public void save(List<Department> departments) {
        departmentRepository.deleteAll();
        departmentRepository.saveAll(departments);
    }
}
