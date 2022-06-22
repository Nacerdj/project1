package com.app.pojo;

public class Reimbursement {
	
	private int reimbursementId;
	private int userId;
	private String reason;
	private int amount;
	private String status;
	
	public Reimbursement() {
		super();
	}
	public Reimbursement(int reimbursementId, int userId, String reason, int amount, String status) {
		super();
		this.reimbursementId = reimbursementId;
		this.userId = userId;
		this.reason = reason;
		this.amount = amount;
		this.status = status;
	}
	public int getReimbursementId() {
		return reimbursementId;
	}
	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", reason=" + reason
				+ ", userId=" + userId + ", amount=" + amount + ", status=" + status + "]";
	}
	
}
