package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Bank;
import com.shkrub.onlineConverter.entities.Department;
import com.shkrub.onlineConverter.repositories.BankRepository;
import com.shkrub.onlineConverter.repositories.DepartmentRepository;
import com.shkrub.onlineConverter.service.BankService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class BankServiceImpl implements BankService {
    private final DepartmentRepository departmentRepository;
    private final BankRepository bankRepository;

    public BankServiceImpl(DepartmentRepository departmentRepository, BankRepository bankRepository) {
        this.departmentRepository = departmentRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    @Transactional
    public List<Bank> getAll() {
        return (List<Bank>) bankRepository.findAll();
    }

    @Override
    @Transactional
    public Set<Bank> getAllByCityId(Long id) {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        Set<Bank> banks = new TreeSet<>();
        for (Department department: departments){
            if(department.getCity().getId().equals(id)){
                banks.add(department.getBank());
            }
        }
        return banks;
    }

    @Override
    @Transactional
    public void save(List<Bank> banks) {
        bankRepository.deleteAll();
        bankRepository.saveAll(banks);
    }
}
