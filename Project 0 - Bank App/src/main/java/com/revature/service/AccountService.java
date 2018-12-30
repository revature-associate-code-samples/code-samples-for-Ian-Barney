package com.revature.service;

import java.util.List;

import com.revature.dao.AccountDao;
import com.revature.pojos.Account;
import com.revature.pojos.User;

public class AccountService {
	static AccountDao accountDao = new AccountDao();
	
	public void createAccount(int type, int user) {
		Account account = new Account(0, type, 0, user);
		accountDao.save(account);
	}
	
	public void withdraw(double withdrawl, Account account) {
		double newBalance = account.getBalance() - withdrawl;
		account.setBalance(newBalance);
		accountDao.update(account);
	}
	
	public void deposit(double deposit, Account account) {
		double newBalance = account.getBalance() + deposit;
		account.setBalance(newBalance);
		accountDao.update(account);
	}
	
	public List<Account> getUserAccounts(User user) {
		List<Account> accounts = accountDao.findAll();
		return accounts;
	}
}
