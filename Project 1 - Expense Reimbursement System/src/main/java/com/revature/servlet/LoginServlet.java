package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojo.User;
import com.revature.service.UserService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	static UserService userService = new UserService();
	private static Logger logger = Logger.getLogger(LoginServlet.class);
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		User u = mapper.readValue(req.getInputStream(), User.class);
		logger.trace("USER: " + u.getUsername() + " PASSWORD: " + u.getPassword());
		
		
		//consult user service to obtain User with this info
		User user = userService.login(u.getUsername(), u.getPassword()); 
		
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		String text = "";
		
		if(user == null) {
			req.getRequestDispatcher("partials/error-login.html").forward(req, resp);
		}
		else {
			//successful log in 
			
			//Add user to session
			HttpSession session = req.getSession();
			//will return current session if one exists
			//creates new session and returns it if none exists
			session.setAttribute("user", user);
			logger.trace("ADDING USER TO SESSION: " + session.getId());
			if(user.getRoleID()==1) {
				req.getRequestDispatcher("partials/reimbursement.html").forward(req,resp);
			} else {
				req.getRequestDispatcher("partials/employee.html").forward(req,resp);
			}
			//render home view
			//redirect to home servlet
			
		}
	}

}
