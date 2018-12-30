package com.revature.service;

import com.revature.dao.DAO;
import com.revature.dao.TypeDAO;
import com.revature.pojo.Type;

public class TypeService {
	static DAO<Type, Integer> tDao = new TypeDAO();
	public String getTypeByID(int id) {
		return tDao.findById(id).getName();
	}
}
