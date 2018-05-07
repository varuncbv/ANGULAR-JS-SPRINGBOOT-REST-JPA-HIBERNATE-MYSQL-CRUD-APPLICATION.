package com.letsstartcoding.angularjsrestapidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letsstartcoding.angularjsrestapidemo.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByName(String name);
}