package com.cg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.entity.Employee;
import com.cg.exception.NoEmployeeException;
import com.cg.service.EmployeeService;

@RestController
public class EmployeeRestController {
	@Autowired
	EmployeeService eService;

	// http://localhost:8080/EmployeeAccApp/employeelist
	@GetMapping("/employeelist")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		try {
			List<Employee> eList = eService.findAllEmployee();
			if (eList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(eList, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/create
	@PostMapping("/create")
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee e) {
		try {
			Employee emp = eService.addEmployee(e);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/find/id
	@GetMapping("/find/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(eService.findById(id), HttpStatus.OK);
		} catch (NoEmployeeException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/update/id
	@PutMapping("update/{id}")
	public ResponseEntity<Employee> modifyEmployee(@PathVariable("id") int id, @RequestBody Employee emp) {
		try {
			return new ResponseEntity<>(eService.modifyEmployee(id, emp), HttpStatus.OK);
		} catch (NoEmployeeException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/delete/id
	@DeleteMapping("delete/{id}")
	public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") int id) {
		try {
			return new ResponseEntity(eService.deleteEmployee(id) ? "deleted" : "not deleted", HttpStatus.OK);
		} catch (NoEmployeeException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
