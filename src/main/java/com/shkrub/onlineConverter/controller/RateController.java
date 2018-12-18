package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Rate;
import com.shkrub.onlineConverter.service.RateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("rates")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<Rate> getAllRateByDepartment(@RequestParam(name = "department") Long id){
        return rateService.getAllByDepartmentId(id);
    }

    @GetMapping("convert")
    public List<Rate> getAllProfitableRate(@RequestParam(name = "from") String from,
                                           @RequestParam(name = "to") String to){
        return rateService.getProfitableValue(from, to);
    }

    @GetMapping("convertRegion")
    public List<Rate> getAllProfitableRateByRegion(@RequestParam(name = "from") String from,
                                                   @RequestParam(name = "to") String to,
                                                   @RequestParam(name = "region") Long regionId){
        return rateService.getProfitableValueByRegionId(from, to, regionId);
    }

    @GetMapping("convertCity")
    public List<Rate> getAllProfitableRateByCity(@RequestParam(name = "from") String from,
                                                   @RequestParam(name = "to") String to,
                                                   @RequestParam(name = "city") Long cityId){
        return rateService.getProfitableValueByCityId(from, to, cityId);
    }
}
