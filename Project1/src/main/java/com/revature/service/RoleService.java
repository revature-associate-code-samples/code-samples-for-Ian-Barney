package com.revature.service;

import com.revature.dao.DAO;
import com.revature.dao.RoleDAO;
import com.revature.pojo.Role;

public class RoleService {
	static DAO<Role, Integer> rDao = new RoleDAO();
	public String getRoleByID(int id) {
		return rDao.findById(id).getName();
	}
}
