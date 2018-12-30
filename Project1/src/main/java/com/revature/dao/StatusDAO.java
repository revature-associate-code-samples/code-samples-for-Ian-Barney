package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojo.Role;
import com.revature.pojo.Status;
import com.revature.util.ConnectionFactory;

public class StatusDAO implements DAO<Status, Integer>{

	@Override
	public List<Status> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status findById(Integer id) {
		Status s = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from ERS_REIMBURSEMENT_STATUS where REIMB_STATUS_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Status();
				s.setStatusID(rs.getInt(1));
				s.setName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public Status save(Status obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Status update(Status obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Status obj) {
		// TODO Auto-generated method stub
		
	}

}
