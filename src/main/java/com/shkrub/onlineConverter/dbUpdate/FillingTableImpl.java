package com.shkrub.onlineConverter.dbUpdate;

import com.shkrub.onlineConverter.entities.*;
import com.shkrub.onlineConverter.repositories.*;
import com.shkrub.onlineConverter.service.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.shkrub.onlineConverter.parser.JsonParser.*;

@Component
public class FillingTableImpl implements FillingTable {
    private final RegionService regionService;
    private final CityService cityService;
    private final BankService bankService;
    private final DepartmentService departmentService;
    private final RateService rateService;


    public FillingTableImpl(RegionService regionService, CityService cityService, BankService bankService,
                            DepartmentService departmentService, RateService rateService) {
        this.regionService = regionService;
        this.cityService = cityService;
        this.bankService = bankService;
        this.departmentService = departmentService;
        this.rateService = rateService;
    }

    @Override
    @Transactional
    public void save() {
        regionService.save(getRegions());
        cityService.save(getCities());
        bankService.save(getBanks());
        departmentService.save(getDepartments());
        rateService.save(getRates());
    }

    @Override
    @Transactional
    public void update() {
        List<Rate> newRates = getRates();
        rateService.updateAll(newRates);
    }
}
