package com.revature.test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.dao.StatusDAO;

public class StatusDAOTest {

	@Test
	public void testFindByID() {
		StatusDAO s = new StatusDAO();
		assertTrue(s.findById(1).getName().equals("APPROVED"));
	}

}
