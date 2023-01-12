package com.cg.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "new_emptbl")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int empId;
	private String empName;
	private long empSal;

	@OneToOne(cascade = CascadeType.ALL)
	private Account account;

	public Employee() {
		super();
	}

	public Employee(int empId, String empName, long empSal, Account account) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSal = empSal;
		this.account = account;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public long getEmpSal() {
		return empSal;
	}

	public void setEmpSal(long empSal) {
		this.empSal = empSal;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName + ", empSal=" + empSal + ", account=" + account
				+ "]";
	}

}
