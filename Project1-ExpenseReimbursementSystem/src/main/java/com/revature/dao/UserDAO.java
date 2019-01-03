package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class UserDAO implements DAO<User,Integer>{

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from ERS_USERS order by ERS_USERS_ID";

			// STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();

			// ResultSet Interface - represents the result set of a DB query
			ResultSet rs = statement.executeQuery(query);

			// returns false when there are no more rows in result set
			while (rs.next()) {
				User temp = new User();
				temp.setUserID(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setFirstName(rs.getString(4));
				temp.setLastName(rs.getString(5));
				temp.setEmail(rs.getString(6));
				temp.setRoleID(rs.getInt(7));
				users.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findById(Integer id) {
		User u = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from ERS_USERS where ERS_USER_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUserID(rs.getInt(1));
				u.setUsername(rs.getString(2));
				u.setPassword(rs.getString(3));
				u.setFirstName(rs.getString(4));
				u.setLastName(rs.getString(5));
				u.setEmail(rs.getString(6));
				u.setRoleID(rs.getInt(7));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public User save(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User update(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(User obj) {
		// TODO Auto-generated method stub
		
	}

	
	
}
