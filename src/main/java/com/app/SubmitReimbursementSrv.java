package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.jdbc.JdbcOperations;
import com.app.pojo.User;

public class SubmitReimbursementSrv extends HttpServlet {
	
	private JdbcOperations jdbcOperations;
	
	public SubmitReimbursementSrv() {
		jdbcOperations = new JdbcOperations();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> HomePage </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");

		req.getRequestDispatcher("/emp_header.html").include(req, resp);

		req.getRequestDispatcher("/submitReimbursement.html").include(req, resp);
	
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String reason = req.getParameter("reason");
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		HttpSession sessionObj = req.getSession();
		
		User loggedInUser = (User) sessionObj.getAttribute("loggedInUser");
		
		boolean result = jdbcOperations.submitReimbursement(reason, amount, loggedInUser);
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> HomePage </title>");
		out.println("</head>");
		out.println("<body>");


		if(result) {
				req.getRequestDispatcher("/emp_header.html").include(req, resp);
				out.println("<h2> Submitted Reimbursement successfully </h2>");
				req.getRequestDispatcher("/emp_homePage.html").include(req, resp);
		}else {
			out.println("<h2 style='color:red'> Submit Reimbursement Failed  </h2>");
			req.getRequestDispatcher("/login.html").include(req, resp);
		}
		
		out.println("</body>");
		out.println("</html>");

	
	}
	
}
