package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class LoadViewsServlet
 */
public class LoadViewsServlet extends HttpServlet {

	private static Logger log = Logger.getLogger(LoadViewsServlet.class);
    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String resourcePath = "partials/" + process(req, resp) +".html";
		System.out.println(resourcePath);
		req.getRequestDispatcher(resourcePath).forward(req, resp);
	}
	
	static String process(HttpServletRequest req, HttpServletResponse resp) {
		log.info("LOAD VIEW REQUEST SENT TO: " + req.getRequestURI());
		switch(req.getRequestURI()) {
		case "/servlet-app/info.view":
			return "info";
		case "/servlet-app/about.view":
			return "about";
		}
		
		return null;
	}

}
