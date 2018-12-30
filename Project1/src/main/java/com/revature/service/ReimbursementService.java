package com.revature.service;

import java.sql.Date;
import java.util.List;

import com.revature.dao.ReimbursementDAO;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.Transaction;

public class ReimbursementService {
	static ReimbursementDAO rDAO = new ReimbursementDAO();
	public List<Transaction> getAll(){
		return rDAO.findAll();
	}
	
	public List<Transaction> getAll(int id){
		return rDAO.findAll(id);
	}
	
	public void changeStatus(int id, int status, int user,Date date) {
		rDAO.update(id,status,user,date);
	}
	
	public Reimbursement insertReimbursement(Reimbursement reimbursement) {
		return rDAO.save(reimbursement);
	}
}
