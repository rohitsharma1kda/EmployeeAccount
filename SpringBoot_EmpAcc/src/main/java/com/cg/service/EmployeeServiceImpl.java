package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.EmployeeRepository;
import com.cg.entity.Employee;
import com.cg.exception.NoEmployeeException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository eRepo;

	@Override
	@Transactional
	public Employee addEmployee(Employee e) {
		return eRepo.save(e);
	}

	@Override
	public List<Employee> findAllEmployee() {
		return eRepo.findAll();
	}

	@Override
	public Employee findById(int id) throws NoEmployeeException {
		Optional<Employee> emp = eRepo.findById(id);
		if (emp.isPresent())
			return emp.get();

		throw new NoEmployeeException("employee not found");
	}

	@Override
	@Transactional
	public Employee modifyEmployee(int id, Employee e) throws NoEmployeeException {
		Optional<Employee> find = eRepo.findById(id);
		if (find.isPresent()) {
			deleteEmployee(id);
			return eRepo.save(e);
		} else {
			throw new NoEmployeeException("employee not found. cannot update.");
		}
	}

	@Override
	@Transactional
	public boolean deleteEmployee(int id) throws NoEmployeeException {

		eRepo.deleteById(id);
		Optional<Employee> find = eRepo.findById(id);
		if (find.isPresent())
			return false;

		return true;
	}

}
