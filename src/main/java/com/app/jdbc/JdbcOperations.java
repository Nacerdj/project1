package com.app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.pojo.Reimbursement;
import com.app.pojo.User;

public class JdbcOperations {
	
	private  Connection con;
	
	private void establishConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/reimbursement_db", "root", "Nacer1264@");
		}catch(ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean validateLogin(String userName, String password){
		establishConnection();
		boolean result = false;
		String query = "select count(*) from user_tab where userName=? and pwd=?";
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setString(1, userName);
			pStmtObj.setString(2, password);
			
			ResultSet rsObj = pStmtObj.executeQuery();
			rsObj.next();
			int count = rsObj.getInt(1);
			if(count != 0) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public User getUser(String userName, String password){
		establishConnection();
		User user = null;
		String query = "select * from user_tab where userName=? and pwd=?";
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setString(1, userName);
			pStmtObj.setString(2, password);
			
			ResultSet rsObj = pStmtObj.executeQuery();
			while(rsObj.next()) {
				user = new User();
				user.setUserId(rsObj.getInt(1));
				user.setUserName(rsObj.getString("userName"));
				user.setPassword(rsObj.getString("pwd"));
				user.setFirstName(rsObj.getString("firstName"));
				user.setLastName(rsObj.getString("lastName"));
				user.setRole(rsObj.getString("role"));
				user.setEmailId(rsObj.getString("emailId"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public boolean submitReimbursement(String reason, int amount, User loggedInUser) {
		establishConnection();
		String query = "insert into reimbursement_info(userId, reason, amount, status) values(?, ?, ?, ?)";
		boolean result = false;
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setInt(1, loggedInUser.getUserId());
			pStmtObj.setString(2, reason);
			pStmtObj.setInt(3, amount);
			pStmtObj.setString(4, "pending");
			
			int r = pStmtObj.executeUpdate();
			if(r!=0) {
				result = true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Reimbursement> viewReimbursements(int userId, String status) {
		 establishConnection();
		 String query = "";
		 PreparedStatement pStmtObj = null;
		 
		 List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		 try {
			 
			 if(userId == 0 && status.equals("all")) {
				 query = "select * from reimbursement_info";
				 pStmtObj = con.prepareStatement(query);
			 }else if (userId != 0 && status.equals("all")) {
				 query = "select * from reimbursement_info where userId = ?";
				 pStmtObj = con.prepareStatement(query);
				 pStmtObj.setInt(1, userId);
			 }else if(userId == 0 && !status.equals("all")) {
				 query = "select * from reimbursement_info where status = ?";
				 pStmtObj = con.prepareStatement(query);
				 pStmtObj.setString(1, status);
			 }else if(status.equals("resolved")) {
				 query = "select * from reimbursement_info where status != 'pending' and userId = ?";
				 pStmtObj = con.prepareStatement(query);
				 pStmtObj.setInt(1, userId);
			 }else {
				 query = "select * from reimbursement_info where userId = ? and status = ?";
				 pStmtObj = con.prepareStatement(query);
				 pStmtObj.setInt(1, userId);
				 pStmtObj.setString(2, status);
			 }
			 
			ResultSet rsObj = pStmtObj.executeQuery();
			while(rsObj.next()) {
				Reimbursement rObj = new Reimbursement();
				rObj.setReimbursementId(rsObj.getInt(1));
				rObj.setUserId(rsObj.getInt(2));
				rObj.setReason(rsObj.getString(3));
				rObj.setAmount(rsObj.getInt(4));
				rObj.setStatus(rsObj.getString(5));
				reimbursements.add(rObj);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 return reimbursements;
	}
	
	public boolean updateUser(User loggedInUser) {
		establishConnection();
		String query = "update user_tab set userName = ?, pwd=?, firstName=?, lastName=?, emailId = ? where userid = ?";
		boolean result = false;
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setString(1, loggedInUser.getUserName());
			pStmtObj.setString(2, loggedInUser.getPassword());
			pStmtObj.setString(3, loggedInUser.getFirstName());
			pStmtObj.setString(4, loggedInUser.getLastName());
			pStmtObj.setString(5, loggedInUser.getEmailId());
			pStmtObj.setInt(6, loggedInUser.getUserId());
			
			int count = pStmtObj.executeUpdate();
			result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<User> getAllEmployees(){
		establishConnection();
		String query = "select * from user_tab where role='employee'";
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			ResultSet rsObj  = pStmtObj.executeQuery();
			while(rsObj.next()) {
				User user = new User();
				user.setUserId(rsObj.getInt(1));
				user.setUserName(rsObj.getString(2));
				user.setPassword(rsObj.getString(3));
				user.setFirstName(rsObj.getString(4));
				user.setLastName(rsObj.getString(5));
				user.setRole(rsObj.getString(6));
				user.setEmailId(rsObj.getString(7));
				
				users.add(user);
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return users;
	}
	
	public boolean updateStatus(int rId, String status) {
		String query = "update reimbursement_info set status = ? where reimbursementId = ?";
		establishConnection();
		boolean result = false;
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			
			pStmtObj.setString(1, status);
			pStmtObj.setInt(2, rId);
			
			pStmtObj.executeUpdate();
			
			result = true;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return true;
	}
	
	public String getEmailId(int rId) {
		establishConnection();
		String query = "select u.emailId from reimbursement_info r join user_tab u where r.userId = u.userid "+
				  " and r.reimbursementId=?";
		String result = "";
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setInt(1, rId);
			
			ResultSet rsObj = pStmtObj.executeQuery();
			rsObj.next();
			result = rsObj.getString(1);
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean registerEmployee(User user) {
		establishConnection();
		String query = "insert into user_tab (userName, pwd, firstName, lastName, role, emailId) \r\n" + 
				"values (?, ?, ?, ?, ?, ?)";
		boolean result = false;
		try {
			PreparedStatement pStmtObj = con.prepareStatement(query);
			pStmtObj.setString(1, user.getUserName());
			pStmtObj.setString(2, user.getPassword());
			pStmtObj.setString(3, user.getFirstName());
			pStmtObj.setString(4, user.getLastName());
			pStmtObj.setString(5, user.getRole());
			pStmtObj.setString(6, user.getEmailId());
			
			int count = pStmtObj.executeUpdate();
			 result = true;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
