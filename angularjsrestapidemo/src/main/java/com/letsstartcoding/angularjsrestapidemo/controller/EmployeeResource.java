package com.letsstartcoding.angularjsrestapidemo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.letsstartcoding.angularjsrestapidemo.model.Employee;
import com.letsstartcoding.angularjsrestapidemo.sevice.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeResource {

	private EmployeeService employeeService;

	public EmployeeResource(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@RequestMapping(value = "employee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getAllEmployees() {
		return employeeService.findAll();
	}

	@RequestMapping(value = "employee", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) throws URISyntaxException {
		try {
			Employee result = employeeService.save(employee);
			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (EntityExistsException e) {
			return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "employee", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) throws URISyntaxException {
		if (employee.getId() == null) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}

		try {
			Employee result = employeeService.update(employee);

			return ResponseEntity.created(new URI("/api/employee/" + result.getId())).body(result);
		} catch (EntityNotFoundException e) {
			return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
		employeeService.delete(id);

		return ResponseEntity.ok().build();
	}
}
