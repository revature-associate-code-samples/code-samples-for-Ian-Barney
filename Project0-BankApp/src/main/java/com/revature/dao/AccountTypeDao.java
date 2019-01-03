package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojos.Account;
import com.revature.pojos.AccountType;
import com.revature.pojos.User;
import com.revature.util.ConnectionFactory;

public class AccountTypeDao implements DAO<AccountType, Integer>{

	@Override
	public List<AccountType> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountType findById(Integer id) {
		AccountType a = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM ACCOUNT_TYPE WHERE TYPE_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new AccountType(rs.getInt(1), rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public AccountType save(AccountType obj) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO ACCOUNT_TYPE (TYPE_NAME) VALUES(?)";
			String[] keyNames = {"TYPE_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getName());
			
			int numRows = ps.executeUpdate();
			
			if(numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while(pk.next()) {
					obj.setTypeID(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public AccountType update(AccountType obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(AccountType obj) {
		// TODO Auto-generated method stub
		
	}

}
