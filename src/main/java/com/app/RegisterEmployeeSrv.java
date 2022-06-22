package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.jdbc.JdbcOperations;
import com.app.pojo.User;

public class RegisterEmployeeSrv extends HttpServlet {
	
	private JdbcOperations jdbcOperations;
	
	public RegisterEmployeeSrv() {
		jdbcOperations = new JdbcOperations();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> Register Employee </title>");
		out.println("</head>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<body style='background-color:#4be6e6'>");
		out.println("<form action='regEmployee' method='post'>");
		out.println("<table>");
		out.println("<tr><td>UserName</td><td>: </td><td><input type='text' name='userName' placeholder='username'  />  </td></tr>");
		out.println("<tr><td>Password</td><td>: </td><td><input type='text' name='pwd' placeholder='password' />  </td></tr>");
		out.println("<tr><td>FirstName</td><td>: </td><td><input type='text' name='fName' placeholder='fName' />  </td></tr>");
		out.println("<tr><td>LastName</td><td>: </td><td><input type='text' name='lName' placeholder='lName' />  </td></tr>");
		out.println("<tr><td>Role</td><td>: </td><td><input type='text' name='role' placeholder='role' />  </td></tr>");
		out.println("<tr><td>Email Id</td><td>: </td><td><input type='text' name='emailid' placeholder='emailid' />  </td></tr>");
		out.println("<tr><td colspan='3'><input type='submit' /> </td></tr>");
		out.println("</table></form></body></html>");
		

	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User newUser = new User();
		newUser.setUserName(req.getParameter("userName"));
		newUser.setPassword(req.getParameter("pwd"));
		newUser.setFirstName(req.getParameter("fName"));
		newUser.setLastName(req.getParameter("lName"));
		newUser.setRole(req.getParameter("role"));
		newUser.setEmailId(req.getParameter("emailid"));
	
		boolean result = jdbcOperations.registerEmployee(newUser);
	
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> Register Employee </title>");
		out.println("</head>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<body style='background-color:#4be6e6'>");
		if(result) {
			out.println("<h3> Registration success </h3>");
		}else {
			out.println("<h3> Registration failure  </h3>");
		}
		req.getRequestDispatcher("/mgr_homePage.html").include(req, resp);
		out.println("</body></html>");
	}
	
	
}
