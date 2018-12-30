package com.revature.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.dao.RoleDAO;

public class RoleDAOTest {

	@Test
	public void testFindByID() {
		RoleDAO r = new RoleDAO();
		assertTrue(r.findById(1).getName().equals("Manager"));
	}

}
