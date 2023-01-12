package com.cg.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.dao.AccountRepository;
import com.cg.entity.Account;
import com.cg.exception.NoAccountException;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository aRepo;

	@Override
	@Transactional
	public Account addAccount(Account a) {
		return aRepo.save(a);
	}

	@Override
	public List<Account> findAllAccount() {
		return aRepo.findAll();
	}

	@Override
	public Account findByNumber(int id) throws NoAccountException {
		Optional<Account> acc = aRepo.findById(id);
		if (acc.isPresent())
			return acc.get();

		throw new NoAccountException("account not found");
	}

	@Override
	@Transactional
	public Account modifyAccount(int id, Account a) throws NoAccountException {
		Optional<Account> find = aRepo.findById(id);
		if (find.isPresent()) {
			deleteAccount(id);
			return aRepo.save(a);
		} else {
			throw new NoAccountException("account not found. cannot update.");
		}
	}

	@Override
	@Transactional
	public boolean deleteAccount(int id) throws NoAccountException {
		aRepo.deleteById(id);
		Optional<Account> find = aRepo.findById(id);
		if (find.isPresent())
			return false;

		return true;
	}

}
