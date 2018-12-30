package com.revature.dao;

import java.awt.print.Book;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;

import oracle.jdbc.internal.OracleTypes;

public class UserDao implements DAO<User, Integer> {

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList<User>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "{ call get_all_users(?) }";
			CallableStatement cs = conn.prepareCall(sql);
			cs.registerOutParameter(1, OracleTypes.CURSOR);
			cs.execute();
			
			ResultSet rs = (ResultSet) cs.getObject(1);
			
			while(rs.next()) {
				User temp = new User();
				temp.setUserID(rs.getInt("USER_ID"));
				temp.setUserName(rs.getString(2));
				temp.setPassword(rs.getString(3));
				users.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findById(Integer id) {
		User a = null;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM BANK_USER WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				a = new User(rs.getInt(1),rs.getString(2),rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public User save(User obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO BANK_USER (USERNAME, PASSWORD) VALUES(?,?)";
			String[] keyNames = {"USER_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getUserName());
			ps.setString(2, obj.getPassword());
			
			int numRows = ps.executeUpdate();
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setUserID(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public User update(User obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE BANK_USER SET USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, obj.getUserName());
			ps.setString(2, obj.getPassword());
			ps.setInt(3, obj.getUserID());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(User obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "DELETE FROM BANK_USER WHERE USER_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getUserID());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql exception");
		}
		
	}

}
