package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutSrv extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession sessionObj = req.getSession();
		
		sessionObj.invalidate();
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		

		out.println("<html>");
		out.println("<head>");
		out.println("<title> HomePage </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<body>");
		
		req.getRequestDispatcher("/login.html").include(req, resp);
		
		out.println("</body>");
		out.println("</html>");


		
	
	}
	
}
