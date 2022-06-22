package com.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.app.jdbc.EmailSender;
import com.app.jdbc.JdbcOperations;

public class ReviewReimbursementsSrv extends HttpServlet {


	private JdbcOperations jdbcOperations;
	private EmailSender emailSender;
	
	public ReviewReimbursementsSrv() {
		jdbcOperations = new JdbcOperations();
		emailSender = new EmailSender();
	}
	

	int count = 0;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> ReView Reimbursements </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);
		out.println("<form action='review' method='POST'>");
		out.println("<input type='number' placeholder='enter reimbursement id' name='rId' required /><br><br>");
		out.println("<input type='text' placeholder='update status' name='status' required /><br><br>");
		out.println("<input type='submit' />");
		out.println("</form></body></html>");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int rId = Integer.parseInt(req.getParameter("rId"));
		String status = req.getParameter("status");
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html>");
		out.println("<head>");
		out.println("<title> ReView Reimbursements </title>");
		out.println("</head>");
		out.println("<body style='background-color:#4be6e6'>");
		req.getRequestDispatcher("/mgr_header.html").include(req, resp);

		boolean result = jdbcOperations.updateStatus(rId, status);
	
		if(result) {
			out.println("<h2> Update Success </h2>");
			String r1 = jdbcOperations.getEmailId(rId);
			if(!r1.equals("")) {
				boolean emailSendResult = emailSender.sendEmail(r1, status, rId);
				if(emailSendResult) {
					out.println("<h3> Sent Email Success </h3>");
				}else {
					out.println("<h3> Email Send - failure </h3>");
				}
			}else {
				out.println("<h3> Email Id not found to send status in email </h3>");
			}
		}else {
			out.println("<h2>Update failed </h2>");
		}
		req.getRequestDispatcher("/mgr_homePage.html").include(req, resp);
		out.println("</body></html>");
	}
	
}
