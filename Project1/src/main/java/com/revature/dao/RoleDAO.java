package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.pojo.Role;
import com.revature.pojo.User;
import com.revature.util.ConnectionFactory;

public class RoleDAO implements DAO<Role, Integer>{

	@Override
	public List<Role> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role findById(Integer id) {
		Role r = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			String sql = "select * from ERS_USER_ROLES where ERS_USER_ROLE_ID = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				r = new Role();
				r.setRoleID(rs.getInt(1));
				r.setName(rs.getString(2));
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	@Override
	public Role save(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role update(Role obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Role obj) {
		// TODO Auto-generated method stub
		
	}

}
