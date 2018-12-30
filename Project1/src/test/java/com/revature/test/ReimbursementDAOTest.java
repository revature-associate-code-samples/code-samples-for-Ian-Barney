package com.revature.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.util.List;

import org.junit.Test;

import com.revature.dao.ReimbursementDAO;
import com.revature.pojo.Reimbursement;

public class ReimbursementDAOTest {

	@Test
	public void testFindAll() {
		ReimbursementDAO rd = new ReimbursementDAO();
		List<Reimbursement> reimbursements = rd.findAll();
		assertTrue(reimbursements.get(0).getReimbID()==6);
	}

	@Test
	public void testFindById() {
		ReimbursementDAO rd = new ReimbursementDAO();
		Reimbursement r = rd.findById(6);
		assertTrue(r.getReimbID()==6);
	}


}
