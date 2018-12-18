package com.shkrub.onlineConverter.dbUpdate;

import com.shkrub.onlineConverter.entities.*;
import com.shkrub.onlineConverter.repositories.*;
import com.shkrub.onlineConverter.service.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

import static com.shkrub.onlineConverter.parser.JsonParser.*;

@Service
public class FillingTableImpl implements FillingTable {
    private final RegionRepository regionRepository;
    private final CityRepository cityRepository;
    private final BankRepository bankRepository;
    private final DepartmentRepository departmentRepository;
    private final RateRepository rateRepository;
    private final RegionService regionService;
    private final CityService cityService;
    private final BankService bankService;
    private final DepartmentService departmentService;
    private final RateService rateService;


    public FillingTableImpl(RegionRepository regionRepository, CityRepository cityRepository,
                            BankRepository bankRepository, DepartmentRepository departmentRepository,
                            RateRepository rateRepository, RegionService regionService, CityService cityService,
                            BankService bankService, DepartmentService departmentService, RateService rateService) {
        this.regionRepository = regionRepository;
        this.cityRepository = cityRepository;
        this.bankRepository = bankRepository;
        this.departmentRepository = departmentRepository;
        this.rateRepository = rateRepository;
        this.regionService = regionService;
        this.cityService = cityService;
        this.bankService = bankService;
        this.departmentService = departmentService;
        this.rateService = rateService;
    }

    @Override
    @Transactional
    public void save() {
        regionRepository.saveAll(getRegions());
        cityRepository.saveAll(getCities());
        bankRepository.saveAll(getBanks());
        departmentRepository.saveAll(getDepartments());
        rateRepository.saveAll(getRates());
    }

    @Override
    @Transactional
    public void update() {
        List<Region> newRegions = getRegions();
        List<City> newCities = getCities();
        List<Bank> newBanks = getBanks();
        List<Department> newDepartments = getDepartments();
        List<Rate> newRates = getRates();
        regionService.updateAll(newRegions);
        cityService.updateAll(newCities);
        bankService.updateAll(newBanks);
        departmentService.updateAll(newDepartments);
        rateService.updateAll(newRates);
    }
}
