package com.shkrub.onlineConverter.controller;

import com.shkrub.onlineConverter.entities.Department;
import com.shkrub.onlineConverter.service.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("departments")
public class DepartmentController{
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/city")
    public List<Department> getAllDepartmentsByBankAndCity(@RequestParam(name = "bank") Long bankId,
                                                    @RequestParam(name = "city") Long cityId){
        return departmentService.getAllByBankAndCityId(bankId, cityId);
    }

    @GetMapping("/region")
    public List<Department> getAllDepartmentsByBankAndRegion(@RequestParam(name = "bank") Long bankId,
                                                      @RequestParam(name = "region") Long regionId){
        return departmentService.getAllByBankAndRegionId(bankId, regionId);
    }

    @GetMapping()
    public List<Department> getAllDepartmentsByBank(@RequestParam(name = "bank") Long id){
        return departmentService.getAllByBank(id);
    }
}
