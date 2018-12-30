package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Reimbursement;
import com.revature.pojo.Transaction;
import com.revature.util.ConnectionFactory;

public class ReimbursementDAO implements DAO<Reimbursement, Integer> {

	@Override
	public List findAll() {
		List<Transaction> reimbursements = new ArrayList<Transaction>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			System.out.println("inside try");
			String query = "SELECT ER.REIMB_ID, EU.ERS_USERNAME, ER.REIMB_AMOUNT, ER.REIMB_DESCRIPTION, ER.REIMB_RESOLVED, ER.REIMB_SUBMITTED, ERS.REIMB_STATUS, ERT.REIMB_TYPE, EUR.ERS_USERNAME FROM ERS_REIMBURSEMENT ER\r\n"
					+ "JOIN ERS_REIMBURSEMENT_STATUS ERS ON ER.REIMB_STATUS_ID = ERS.REIMB_STATUS_ID\r\n"
					+ "JOIN ERS_REIMBURSEMENT_TYPE ERT ON ER.REIMB_TYPE_ID = ERT.REIMB_TYPE_ID\r\n"
					+ "JOIN ERS_USERS EU ON ER.REIMB_AUTHOR = EU.ERS_USERS_ID "
					+ "JOIN ERS_USERS EUR ON ER.REIMB_RESOLVER = EUR.ERS_USERS_ID " + "ORDER BY ER.REIMB_ID";

			// STATEMENT INTERFACE - implementation exposed via connection
			Statement statement = conn.createStatement();

			// ResultSet Interface - represents the result set of a DB query
			ResultSet rs = statement.executeQuery(query);

			// returns false when there are no more rows in result set
			while (rs.next()) {
				Transaction temp = new Transaction();
				temp.setReimbID(rs.getInt(1));
				temp.setReimbAuthor(rs.getString(2));
				temp.setReimbAmount(rs.getInt(3));
				temp.setReimbDescription(rs.getString(4));
				temp.setReimbResolved(rs.getDate(5));
				temp.setReimbSubmitted(rs.getDate(6));
				System.out.println(temp.getReimbSubmitted());
				temp.setReimbStatus(rs.getString(7));
				temp.setReimbType(rs.getString(8));
				temp.setReimbResolver(rs.getString(9));
				reimbursements.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;
	}

	@Override
	public Reimbursement findById(Integer id) {
		Reimbursement r = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from ERS_REIMBURSEMENT where REIMB_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Reimbursement();
				r.setReimbID(rs.getInt(1));
				System.out.println(r.getReimbID());
				r.setReimbAmount(rs.getDouble(2));
				r.setReimbSubmitted(rs.getDate(3));
				
				r.setReimbResolved(rs.getDate(4));
				r.setReimbDescription(rs.getString(5));
				r.setReimbAuthor(rs.getInt(7));
				r.setReimbResolver(rs.getInt(8));
				r.setReimbStatusID(rs.getInt(8));
				r.setReimbTypeID(rs.getInt(10));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("exception");
		}
		return r;
	}

	@Override
	public Reimbursement save(Reimbursement obj) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "INSERT INTO ERS_REIMBURSEMENT"
					+ " (REIMB_AMOUNT,REIMB_SUBMITTED,REIMB_RESOLVED,REIMB_DESCRIPTION,"
					+ "REIMB_RECEIPT,REIMB_AUTHOR,REIMB_RESOLVER,REIMB_STATUS_ID,REIMB_TYPE_ID) VALUES(?,?,?,?,?,?,?,?,?)";
			String[] keyNames = { "Reimb_Id" };

			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setDouble(1, obj.getReimbAmount());
			ps.setDate(2, obj.getReimbSubmitted());
			ps.setDate(3, obj.getReimbResolved());
			ps.setString(4, obj.getReimbDescription());
			ps.setString(5, obj.getReimbReceipt());
			ps.setInt(6, obj.getReimbAuthor());
			ps.setInt(7, obj.getReimbResolver());
			ps.setInt(8, obj.getReimbStatusID());
			ps.setInt(9, obj.getReimbTypeID());

			int numRows = ps.executeUpdate();
			if (numRows > 0) {
				ResultSet pk = ps.getGeneratedKeys();
				while (pk.next()) {
					obj.setReimbID(pk.getInt(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public Reimbursement update(int id, int status, int user, Date date) {
		System.out.println("update dao method");
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "update ERS_REIMBURSEMENT set REIMB_STATUS_ID = ?, REIMB_RESOLVER = ?, REIMB_RESOLVED = ? where REIMB_ID= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setInt(2, user);
			ps.setDate(3, date);
			ps.setInt(4, id);
			ps.executeUpdate();
			System.out.println("finished update");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("exception");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Reimbursement obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public Reimbursement update(Reimbursement obj) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Transaction> findAll(int id) {
		List<Transaction> reimbursements = new ArrayList<Transaction>();

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			System.out.println("inside try");
			String query = "SELECT ER.REIMB_ID, EU.ERS_USERNAME, ER.REIMB_AMOUNT, ER.REIMB_DESCRIPTION, ER.REIMB_RESOLVED, ER.REIMB_SUBMITTED, ERS.REIMB_STATUS, ERT.REIMB_TYPE, EUR.ERS_USERNAME FROM ERS_REIMBURSEMENT ER\r\n"
					+ "JOIN ERS_REIMBURSEMENT_STATUS ERS ON ER.REIMB_STATUS_ID = ERS.REIMB_STATUS_ID\r\n"
					+ "JOIN ERS_REIMBURSEMENT_TYPE ERT ON ER.REIMB_TYPE_ID = ERT.REIMB_TYPE_ID\r\n"
					+ "JOIN ERS_USERS EU ON ER.REIMB_AUTHOR = EU.ERS_USERS_ID "
					+ "JOIN ERS_USERS EUR ON ER.REIMB_RESOLVER = EUR.ERS_USERS_ID " + "WHERE ER.REIMB_AUTHOR = ?";

			String[] keyNames = { "Reimb_Id" };

			PreparedStatement ps = conn.prepareStatement(query, keyNames);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			// returns false when there are no more rows in result set
			while (rs.next()) {
				Transaction temp = new Transaction();
				temp.setReimbID(rs.getInt(1));
				temp.setReimbAuthor(rs.getString(2));
				temp.setReimbAmount(rs.getInt(3));
				temp.setReimbDescription(rs.getString(4));
				temp.setReimbResolved(rs.getDate(5));
				temp.setReimbSubmitted(rs.getDate(6));
				temp.setReimbStatus(rs.getString(7));
				temp.setReimbType(rs.getString(8));
				temp.setReimbResolver(rs.getString(9));
				reimbursements.add(temp);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbursements;

	}
}
