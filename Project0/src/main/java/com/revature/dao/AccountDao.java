package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Account;
import com.revature.util.ConnectionFactory;

public class AccountDao implements DAO<Account, Integer> {

	public List<Account> findByUser(int id) {
		List<Account> accounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE USER_ID = ? ORDER BY ACCOUNT_ID";

			// STATEMENT INTERFACE - implementation exposed via connection
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Account temp = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				accounts.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public List<Account> findAll() {
		List<Account> accounts = new ArrayList<Account>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "SELECT * FROM BANK_ACCOUNT ORDER BY ACCOUNT_ID";

			// STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();

			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) {
				Account temp = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
				accounts.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

	@Override
	public Account findById(Integer id) {
		Account a = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "SELECT * FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new Account(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return a;
	}

	@Override
	public Account save(Account obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "INSERT INTO BANK_ACCOUNT (ACCOUNT_TYPE, BALANCE, USER_ID) VALUES(?,?,?)";
			String[] keyNames = { "ACCOUNT_ID" };

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getTypeID());
			ps.setDouble(2, obj.getBalance());
			ps.setInt(3, obj.getUserID());

			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setAccountID(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public Account update(Account obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "UPDATE BANK_ACCOUNT SET BALANCE = ? WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setDouble(1, obj.getBalance());
			ps.setInt(2, obj.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {

		}
		return null;
	}

	@Override
	public void delete(Account obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "DELETE FROM BANK_ACCOUNT WHERE ACCOUNT_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getAccountID());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql exception");
		}
	}

}
