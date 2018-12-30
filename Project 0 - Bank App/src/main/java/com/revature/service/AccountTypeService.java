package com.revature.service;

import com.revature.dao.AccountTypeDao;

public class AccountTypeService {
	static AccountTypeDao accountTypeDao = new AccountTypeDao();
	public String getNameFromID(int id) {
		return accountTypeDao.findById(id).getName();
	}
}
