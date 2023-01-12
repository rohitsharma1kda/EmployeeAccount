package com.cg.service;

import java.util.List;

import com.cg.entity.Employee;
import com.cg.exception.NoEmployeeException;

public interface EmployeeService {
	Employee addEmployee(Employee e);

	List<Employee> findAllEmployee();

	Employee findById(int id) throws NoEmployeeException;

	Employee modifyEmployee(int id, Employee e) throws NoEmployeeException;

	boolean deleteEmployee(int id) throws NoEmployeeException;
}
