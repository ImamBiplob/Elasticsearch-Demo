package com.imambiplob.elasticsearchdemo.controller;

import com.imambiplob.elasticsearchdemo.entity.Employee;
import com.imambiplob.elasticsearchdemo.entity.EsEmployee;
import com.imambiplob.elasticsearchdemo.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.save(employee);

        return ResponseEntity.ok(savedEmployee);
    }

    @GetMapping("/searchByName/{name}")
    public List<EsEmployee> searchByName(@PathVariable String name) throws IOException {
        return employeeService.searchByName(name);
    }

    @GetMapping("/searchByName2/{name}")
    public List<EsEmployee> searchByName2(@PathVariable String name) {
        return employeeService.searchByName2(name);
    }

    @GetMapping("searchBySalary/{startingSalary}/{endingSalary}")
    public List<EsEmployee> searchBySalary(@PathVariable int startingSalary, @PathVariable int endingSalary) {
        return employeeService.searchBySalary(startingSalary, endingSalary);
    }

    @GetMapping("searchByAge/{from}/{to}")
    public List<EsEmployee> searchByAge(@PathVariable int from, @PathVariable int to) {
        return employeeService.searchByAge(from, to);
    }
}
