package com.cg.service;

import java.util.List;

import com.cg.entity.Account;
import com.cg.exception.NoAccountException;

public interface AccountService {
	Account addAccount(Account a);

	List<Account> findAllAccount();

	Account findByNumber(int id) throws NoAccountException;

	Account modifyAccount(int id, Account a) throws NoAccountException;

	boolean deleteAccount(int id) throws NoAccountException;
}
