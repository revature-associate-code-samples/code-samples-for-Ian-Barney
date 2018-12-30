package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.Reimbursement;
import com.revature.pojo.StatusChange;
import com.revature.pojo.Transaction;
import com.revature.pojo.User;
import com.revature.service.ReimbursementService;
import com.revature.service.StatusService;
import com.revature.service.TypeService;
import com.revature.service.UserService;

@WebServlet("/reimbursements")
public class ReimbursementServlet extends HttpServlet {
	static ReimbursementService rService = new ReimbursementService();
	static UserService uService = new UserService();
	static TypeService tService = new TypeService();
	static StatusService sService = new StatusService();
	private static Logger log = Logger.getLogger(ReimbursementServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * todo
		 * convert the ids into their respective names
		 * add a button to change a reimbursement 
		 */
		List<Transaction> reimbursements;
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		log.trace("USER ROLE " + user.getRoleID());
		if(user.getRoleID()==0) {
			reimbursements = rService.getAll(user.getUserID());
		} else {
			reimbursements = rService.getAll();
		}
		// convert to JSON
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimbursements);
		log.trace("FINDING ALL REIMBURSEMENTS. JSON: " + json);

		// send response
		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("ADDING REQUEST");
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimbursement = mapper.readValue(req.getInputStream(), Reimbursement.class);
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		reimbursement.setReimbAuthor(user.getUserID());
		reimbursement.setReimbResolver(1);
		
		java.sql.Date date = new Date(Calendar.getInstance().getTime().getTime());
		reimbursement.setReimbSubmitted(date);
		log.trace(reimbursement.toString());
		rService.insertReimbursement(reimbursement);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.trace("UPDATING STATUS");
		java.sql.Date date = new Date(Calendar.getInstance().getTime().getTime());
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		ObjectMapper mapper = new ObjectMapper();
		StatusChange sc = mapper.readValue(req.getInputStream(), StatusChange.class);
		log.trace("SENDING ID: " + sc.getId() + " AND STATUS " + sc.getStatus() + " AND USER " + user.getUserID() + " AND DATE " + date); 
		rService.changeStatus(sc.getId(), sc.getStatus(), user.getUserID(),date);
	}
}
