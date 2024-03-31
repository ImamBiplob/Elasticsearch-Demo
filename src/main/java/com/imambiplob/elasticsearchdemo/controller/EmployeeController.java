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

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable long employeeId) {
        return employeeService.deleteEmployee(employeeId);
    }

    @GetMapping("/searchByName/{keyword}")  // auto suggest employees where keyword is matching with start of any word from employee name (Implemented 'edge_ngram' analyzer)
    public List<EsEmployee> searchByName(@PathVariable String keyword) throws IOException {
        return employeeService.searchByName(keyword);
    }

    @GetMapping("/searchByName2/{keyword}")  // auto suggest employees where keyword is matching with start of any word from employee name (Implemented 'edge_ngram' analyzer)
    public List<EsEmployee> searchByName2(@PathVariable String keyword) {
        return employeeService.searchByName2(keyword);
    }

    @GetMapping("/searchByName3/{keyword}")  // auto suggest employees where keyword is matching with start of any word from employee name (Implemented 'edge_ngram' analyzer)
    public List<EsEmployee> searchByName3(@PathVariable String keyword) {
        return employeeService.searchByName3(keyword);
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
