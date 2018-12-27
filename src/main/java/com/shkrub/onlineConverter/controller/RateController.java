package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Rate;
import com.shkrub.onlineConverter.service.RateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081", maxAge = 3600)
@RestController
@RequestMapping("rates")
public class RateController {
    private final RateService rateService;

    public RateController(RateService rateService) {
        this.rateService = rateService;
    }

    @GetMapping
    public List<Rate> getAllRateByDepartment(@RequestParam(name = "department") Long id) {
        return rateService.getAllByDepartmentId(id);
    }

    @GetMapping("best")
    public Double getAllProfitableValue(@RequestParam(name = "from") String from,
                                        @RequestParam(name = "to") String to) {
        return rateService.getProfitableValue(from, to);
    }

    @GetMapping("bestByCity")
    public Double getAllProfitableValueByCity(@RequestParam(name = "from") String from,
                                              @RequestParam(name = "to") String to,
                                              @RequestParam(name = "cityId") Long id) {
        return rateService.getProfitableValueByCityId(from, to, id);
    }
}
