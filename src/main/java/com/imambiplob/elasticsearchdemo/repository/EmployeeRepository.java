package com.imambiplob.elasticsearchdemo.repository;

import com.imambiplob.elasticsearchdemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
