package com.app;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.app.jdbc.JdbcOperations;
import com.app.pojo.Reimbursement;
import com.app.pojo.User;

public class ViewEmployeesSrv extends HttpServlet {
	
	private JdbcOperations jdbcOperations;
	public ViewEmployeesSrv() {
		jdbcOperations = new JdbcOperations();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Employees </title>");
		out.println("</head>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<body style='background-color:#4be6e6'>");
			
		List<User> users = jdbcOperations.getAllEmployees();
		
		out.println("<table><tr><Th>UserId</td><th>userName</th><th>Password</th><th>FirstName</th><th>LastName</th><th>EmailId</th></tr>");
		for(User u : users) {
			out.println("<tr><td>"+u.getUserId()+"</td><td>"+u.getUserName()+"</td><td>"
						+u.getPassword()+"</td><td>"+u.getFirstName()+"</td><td>"+u.getLastName()+"</td><td>"+u.getEmailId()+"</td></tr>");
		}
		
		out.println("</table></body></html>");
	    
	}

}
