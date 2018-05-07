package com.letsstartcoding.angularjsrestapidemo.sevice;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.letsstartcoding.angularjsrestapidemo.model.Employee;
import com.letsstartcoding.angularjsrestapidemo.repository.EmployeeRepository;


@Service
public class EmployeeService {
	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee save(Employee employee) {
		if (employee.getId() != null && employeeRepository.exists(employee.getId())) {
			throw new EntityExistsException("There is already existing entity with such ID in the database.");
		}

		return employeeRepository.save(employee);
	}

	public Employee update(Employee employee) {
		if (employee.getId() != null && !employeeRepository.exists(employee.getId())) {
			throw new EntityNotFoundException("There is no entity with such ID in the database.");
		}

		return employeeRepository.save(employee);
	}

	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	public Employee findOne(Integer id) {
		return employeeRepository.findOne(id);
	}

	public void delete(Integer id) {
		employeeRepository.delete(id);
	}
}
