package com.revature.service;

import java.util.List;

import com.revature.dao.UserDao;
import com.revature.pojos.User;

public class UserService {

	static UserDao userDao = new UserDao();

	public boolean createUser(String userName, String password) {
		boolean isUnique = true;
		List<User> users = userDao.findAll();
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				isUnique = false;
			}
		}
		if (isUnique) {
			userDao.save(new User(0, userName, password));
			return true;
		} else {
			System.out.println("That username already exists");
			return false;
		}
	}

	public User loginUser(String userName, String password) {
		List<User> users = userDao.findAll();
		for (User user : users) {
			if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

}
