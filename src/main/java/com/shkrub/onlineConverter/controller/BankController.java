package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Bank;
import com.shkrub.onlineConverter.service.BankService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping
    public final List<Bank> getAllBanks() {
        return bankService.getAll();
    }

    @GetMapping("city/{id}")
    public Set<Bank> getBanksByCity(@PathVariable(name = "id") Long id) {
        return bankService.getAllByCityId(id);
    }
}
