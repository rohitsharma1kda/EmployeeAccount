package com.cg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.dao.EmployeeRepository;
import com.cg.entity.Account;
import com.cg.entity.Employee;
import com.cg.exception.NoEmployeeException;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository repo;

	@InjectMocks
	private EmployeeServiceImpl service;

	private List<Employee> eList;
	private Employee employee;
	private Account account;

	private EmployeeServiceImplTest() {
		service = new EmployeeServiceImpl();
		eList = new ArrayList<>();
	}

	// wrong object data
//	private Account wrongAccData = new Account(101, "savings");
//	private Employee wrongEmpData = new Employee(1, "name", 2000, wrongAccData);

	@BeforeEach
	void init() {
		account = new Account(101, "savings");
		employee = new Employee(1, "name", 2000, account);
		eList.add(employee);
	}

	@Test
	void addEmployeeTest() {
		Mockito.when(service.addEmployee(employee)).thenReturn(employee);
		assertEquals(employee, service.addEmployee(employee));
		verify(repo, Mockito.times(1)).save(employee);
	}

	@Test
	void findAllEmployeeTest() {
		Mockito.when(service.findAllEmployee()).thenReturn(eList);
		assertEquals(eList, service.findAllEmployee());
	}

	@Test
	void findByIdTest() {
		try {
			Mockito.when(repo.findById(1)).thenReturn(Optional.of(employee));
			assertEquals(employee, service.findById(1));
		} catch (NoEmployeeException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void updateEmployeeTest() {
		employee.setEmpName("updated");
		try {
			Mockito.when(repo.findById(1)).thenReturn(Optional.of(employee));
			Mockito.when(service.modifyEmployee(1, employee)).thenReturn(employee);
			assertEquals(employee, service.modifyEmployee(1, employee));
		} catch (NoEmployeeException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

	@Test
	void deleteEmployeeTest() {
		try {
			eList.remove(0);
			Mockito.when(repo.findById(1)).thenReturn(Optional.of(employee)).thenAnswer(i -> service.deleteEmployee(1));
			assertEquals(true, service.deleteEmployee(1));
			assertEquals(eList, service.findAllEmployee());
		} catch (NoEmployeeException ex) {
			ex.getMessage();
			fail("caused exception");
		}
	}

}
