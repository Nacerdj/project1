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

public class ViewAllReimbursementsSrv extends HttpServlet {

	private JdbcOperations jdbcOperations;
	
	public ViewAllReimbursementsSrv() {
		jdbcOperations = new JdbcOperations();
	}
	

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Reimbursements </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<form action='viewAllReimbursements' method='POST'>");
		out.println("<input type='number' placeholder='EmployeeId' name='empId' /> <br><br>");
		out.println("<input type='text' placeholder='status' name='status' /> <br><br>");
		out.println("<input type='submit' /> </form>");
		out.println("<table><thead><tr><th>ReimbursementId</th><th>UserId</th><th>Reason</th><th>Amount</th><th>Status</th></thead><tbody>");
		HttpSession sessionObj = req.getSession();
		
		User loggedInUser = (User) sessionObj.getAttribute("loggedInUser");
		
		List<Reimbursement> reimbursements = jdbcOperations.viewReimbursements(0, "all");
		
		for(Reimbursement rObj : reimbursements) {
			 out.println("<tr><td>"+rObj.getReimbursementId()+"</td><td>"+rObj.getUserId()+"</td><td>"+
					 rObj.getReason()+"</td><td>"+rObj.getAmount()+"</td><td>"+rObj.getStatus()+"</td></tr>");
		}
	
		out.println("</tbody></table></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String eId = req.getParameter("empId");
		int employeeId;
		if(eId == "" || eId == null) {
			employeeId = 0;
		}else {
		   employeeId = Integer.parseInt(eId);	
		}
	     String status = req.getParameter("status");
		
	 	resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Reimbursements </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<form action='viewAllReimbursements' method='POST'>");
		out.println("<input type='text' placeholder='EmployeeId' name='empId' /> <br><br>");
		out.println("<input type='text' placeholder='status' name='status' /> <br><br>");
		out.println("<input type='submit' /> </form><br>");
		out.println("<table><thead><tr><th>ReimbursementId</th><th>UserId</th><th>Reason</th><th>Amount</th><th>Status</th></thead><tbody>");
		
		if(status.equals("") || status==null) {
			status = "all";
		}
		List<Reimbursement> reimbursements = jdbcOperations.viewReimbursements(employeeId, status);
		
		for(Reimbursement rObj : reimbursements) {
			 out.println("<tr><td>"+rObj.getReimbursementId()+"</td><td>"+rObj.getUserId()+"</td><td>"+
					 rObj.getReason()+"</td><td>"+rObj.getAmount()+"</td><td>"+rObj.getStatus()+"</td></tr>");
		}
	
		out.println("</tbody></table></body></html>");

	
	}
	
	
	
}
