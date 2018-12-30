package com.revature.service;

import java.util.List;

import com.revature.dao.DAO;
import com.revature.dao.UserDAO;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;

public class UserService {
	static DAO <User, Integer> uDao = new UserDAO();
	public String getUserByID(int id) {
		return uDao.findById(id).getUsername();
	}
	public User login(String username, String password){
		List<User> users = uDao.findAll();
		for(User u:users) {
			if(u.getUsername().equals(username)&&u.getPassword().equals(password)) {
				return u;
			}
		}
		return null;
	}
	
}
