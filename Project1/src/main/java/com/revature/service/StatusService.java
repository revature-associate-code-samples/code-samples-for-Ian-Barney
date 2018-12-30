package com.revature.service;

import com.revature.dao.DAO;
import com.revature.dao.StatusDAO;
import com.revature.pojo.Status;

public class StatusService {
	static DAO<Status, Integer> sDao = new StatusDAO();
	public String getServiceByID(int id) {
		return sDao.findById(id).getName();
	}
}
