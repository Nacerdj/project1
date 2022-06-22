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

public class UpdateProfileSrv extends HttpServlet {

	private JdbcOperations jdbcOperations;
	
	public UpdateProfileSrv() {
		jdbcOperations = new JdbcOperations();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> Update Profile </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		out.println("<form action='updateProfile' method='POST'>");
		req.getRequestDispatcher("/emp_header.html").include(req, resp);
		out.println("<table><tbody>");
		HttpSession sessionObj = req.getSession();
		
		User loggedInUser = (User) sessionObj.getAttribute("loggedInUser");
		
		out.println("<tr><td>UserName </td><td> : </td><td> <input type='text' name='userName' value='"+loggedInUser.getUserName()+"' /></td></tr>");
		out.println("<tr><td>Password </td><td> : </td><td> <input type='password' name='password' value='"+loggedInUser.getPassword()+"' /></td></tr>");
		out.println("<tr><td>FirstName </td><td> : </td><td> <input type='text' name='fName' value='"+loggedInUser.getFirstName()+"' /></td></tr>");
		out.println("<tr><td>LastName </td><td> : </td><td> <input type='text' name='lName' value='"+loggedInUser.getLastName()+"' /></td></tr>");
		out.println("<tr><td>Role </td><td> : </td><td> "+loggedInUser.getRole()+"</td></tr>");
		out.println("<tr><td>EmailId </td><td> : </td><td> <input type='text' name='emailId' value='"+loggedInUser.getEmailId()+"' /></td></tr>");
		out.println("<tr ><Td colspan='3' rowspan='2'><input type='submit' /></td></tr>");
		out.println("</tbody></table>");
		
		out.println("</form></body></html>");
	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		String fName = req.getParameter("fName");
		String lName = req.getParameter("lName");
		String emailId = req.getParameter("emailId");
				
	    User loggedInUser = (User) req.getSession().getAttribute("loggedInUser");
	
	    loggedInUser.setUserName(userName);
	    loggedInUser.setPassword(password);
	    loggedInUser.setFirstName(fName);
	    loggedInUser.setLastName(lName);
	    loggedInUser.setEmailId(emailId);
	    
	    boolean result = jdbcOperations.updateUser(loggedInUser);
	    
	    resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> Employee Home Page </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		req.getRequestDispatcher("/emp_header.html").include(req, resp);

		if(result)
			out.println("<h2> Updated successfully </h2>");
		else
			out.println("<h2> Updation failed, please try again </h2>");
		req.getRequestDispatcher("/emp_homePage.html").include(req, resp);
	    
	}
	
	
}
