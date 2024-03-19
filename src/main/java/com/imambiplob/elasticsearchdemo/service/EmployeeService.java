package com.imambiplob.elasticsearchdemo.service;

import com.imambiplob.elasticsearchdemo.entity.Employee;
import com.imambiplob.elasticsearchdemo.entity.EsEmployee;
import com.imambiplob.elasticsearchdemo.repository.EmployeeRepository;
import com.imambiplob.elasticsearchdemo.repository.EsEmployeeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {
    private final EsEmployeeService esEmployeeService;

    private final EmployeeRepository employeeRepository;

    private final EsEmployeeRepository esEmployeeRepository;

    public EmployeeService(EsEmployeeService esEmployeeService, EmployeeRepository employeeRepository, EsEmployeeRepository esEmployeeRepository) {
        this.esEmployeeService = esEmployeeService;
        this.employeeRepository = employeeRepository;
        this.esEmployeeRepository = esEmployeeRepository;
    }

    public Employee save(Employee employee) {
        EsEmployee esEmployee = new EsEmployee();

        esEmployee.setName(employee.getName());
        esEmployee.setAge(employee.getAge());
        esEmployee.setDepartment(employee.getDepartment());
        esEmployee.setSalary(employee.getSalary());

        Employee savedEmployee = employeeRepository.save(employee);
        esEmployee.setId(savedEmployee.getId());
        esEmployeeService.saveEmployee(esEmployee);

        return savedEmployee;
    }

    public List<EsEmployee> searchByName(String name) throws IOException {
        return esEmployeeService.searchEmployeeByName(name);
    }

    public List<EsEmployee> searchByName2(String name) {
        return esEmployeeService.searchStringQuery(name);
    }

    public List<EsEmployee> searchBySalary(int startingSalary, int endingSalary) {
        return esEmployeeService.searchEmployeeWithSalaryBetween(startingSalary, endingSalary);
    }

    public List<EsEmployee> searchByAge(int from, int to) {
        return esEmployeeRepository.findByAgeBetween(from, to);
    }

}
