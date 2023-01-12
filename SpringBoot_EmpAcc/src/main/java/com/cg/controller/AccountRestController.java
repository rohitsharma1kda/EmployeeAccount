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

import com.cg.entity.Account;
import com.cg.exception.NoAccountException;
import com.cg.service.AccountService;

@RestController
public class AccountRestController {
	@Autowired
	AccountService aService;

	// http://localhost:8080/EmployeeAccApp/accountlist
	@GetMapping("/accountlist")
	public ResponseEntity<List<Account>> getAllAccount() {
		try {
			List<Account> aList = aService.findAllAccount();
			if (aList.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(aList, HttpStatus.OK);
			}
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/createaccount
	@PostMapping("/createaccount")
	public ResponseEntity<Account> createEmployee(@RequestBody Account a) {
		try {
			Account emp = aService.addAccount(a);
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/findaccount/id
	@GetMapping("/findaccount/{id}")
	public ResponseEntity<Account> getById(@PathVariable("id") int id) {
		try {
			return new ResponseEntity<>(aService.findByNumber(id), HttpStatus.OK);
		} catch (NoAccountException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/updateaccount/id
	@PutMapping("updateaccount/{id}")
	public ResponseEntity<Account> modifyAccount(@PathVariable("id") int id, @RequestBody Account acc) {
		try {
			return new ResponseEntity<>(aService.modifyAccount(id, acc), HttpStatus.OK);
		} catch (NoAccountException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// http://localhost:8080/EmployeeAccApp/deleteaccount/id
	@DeleteMapping("deleteaccount/{id}")
	public ResponseEntity<Account> deleteEmployee(@PathVariable("id") int id) {
		try {
			return new ResponseEntity(aService.deleteAccount(id) ? "deleted" : "not deleted", HttpStatus.OK);
		} catch (NoAccountException ex) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
