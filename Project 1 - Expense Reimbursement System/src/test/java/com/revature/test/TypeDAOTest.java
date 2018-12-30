package com.revature.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.dao.TypeDAO;

public class TypeDAOTest {

	@Test
	public void test() {
		TypeDAO t = new TypeDAO();
		assertTrue(t.findById(1).getName().equals("TRAVEL"));
	}

}
