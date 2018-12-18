package com.shkrub.onlineConverter.service.impl;

import com.shkrub.onlineConverter.entities.Bank;
import com.shkrub.onlineConverter.entities.Department;
import com.shkrub.onlineConverter.repositories.BankRepository;
import com.shkrub.onlineConverter.repositories.DepartmentRepository;
import com.shkrub.onlineConverter.service.BankService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankServiceImpl implements BankService {
    private final DepartmentRepository departmentRepository;
    private final BankRepository bankRepository;

    public BankServiceImpl(DepartmentRepository departmentRepository, BankRepository bankRepository) {
        this.departmentRepository = departmentRepository;
        this.bankRepository = bankRepository;
    }

    @Override
    public List<Bank> getAll() {
        return (List<Bank>) bankRepository.findAll();
    }

    @Override
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
    public Set<Bank> getAllByRegionId(Long id) {
        List<Department> departments = (List<Department>) departmentRepository.findAll();
        Set<Bank> banks= new TreeSet<>();
        for (Department department: departments){
            if (department.getCity().getRegion().getId().equals(id)){
                banks.add(department.getBank());
            }
        }
        return banks;
    }

    @Override
    public void updateAll(List<Bank> newBanks) {
        for(Bank bank : newBanks){
            bankRepository.save(bank);
        }
    }
}
