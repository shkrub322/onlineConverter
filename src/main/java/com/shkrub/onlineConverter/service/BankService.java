package com.shkrub.onlineConverter.service;

import com.shkrub.onlineConverter.entities.Bank;

import java.util.List;
import java.util.Set;

public interface BankService {
    List<Bank> getAll();

    Set<Bank> getAllByCityId(Long id);

    void save(List<Bank> banks);
}
