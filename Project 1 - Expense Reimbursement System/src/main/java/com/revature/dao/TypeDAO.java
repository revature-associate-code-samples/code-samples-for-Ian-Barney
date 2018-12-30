package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojo.Role;
import com.revature.pojo.Type;
import com.revature.util.ConnectionFactory;

public class TypeDAO implements DAO<Type,Integer>{

	@Override
	public List<Type> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type findById(Integer id) {
		Type t = null;
		System.out.println("before connection");
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			System.out.println("inside connection");
			String sql = "select * from ERS_REIMBURSEMENT_TYPE where REIMB_TYPE_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				t = new Type();
				t.setTypeID(rs.getInt(1));
				t.setName(rs.getString(2));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	@Override
	public Type save(Type obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Type update(Type obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Type obj) {
		// TODO Auto-generated method stub
		
	}

}
