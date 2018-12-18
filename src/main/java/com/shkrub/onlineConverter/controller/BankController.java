package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Bank;
import com.shkrub.onlineConverter.service.BankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public final List<Bank> getAllBanks(){
        return bankService.getAll();
    }

    @GetMapping("city/{id}")
    public Set<Bank> getBanksByCity(@PathVariable(name = "id") Long id){
        return bankService.getAllByCityId(id);
    }

    @GetMapping("region/{id}")
    public Set<Bank> getBanksByRegion(@PathVariable(name = "id") Long id){
        return bankService.getAllByRegionId(id);
    }
}
