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

public class LoginSrv extends HttpServlet {

	private JdbcOperations jdbcOperations;

	public LoginSrv() {
		jdbcOperations = new JdbcOperations();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("usrName");
		String password = req.getParameter("pwd");

		boolean result = jdbcOperations.validateLogin(userName, password);

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> HomePage </title>");
		out.println("</head>");
		out.println("<body bgcolor=\"white\">");
		out.println("<body>");

		if(result) {
			User user = jdbcOperations.getUser(userName, password);
			HttpSession sessionObj =  req.getSession(true);
			sessionObj.setAttribute("loggedInUser", user);
			
			if(user.getRole().equals("employee")) {
				req.getRequestDispatcher("/emp_header.html").include(req, resp);
				req.getRequestDispatcher("/emp_homePage.html").include(req, resp);
			}else {
				req.getRequestDispatcher("/mgr_header.html").include(req, resp);
				req.getRequestDispatcher("/mgr_homePage.html").include(req, resp);
			}
			
		}else {
			out.println("<h2 style='color:red'> Invalid Credentials  </h2>");
			req.getRequestDispatcher("/login.html").include(req, resp);
		}
		
		out.println("</body>");
		out.println("</html>");

	}

}
