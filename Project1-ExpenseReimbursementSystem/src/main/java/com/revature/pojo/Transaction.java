package com.revature.pojo;

import java.sql.Date;

public class Transaction {
	private int reimbID;
	private double reimbAmount;
	private Date reimbSubmitted;
	private Date reimbResolved;
	private String submittedString;
	private String resolvedString;
	private String reimbDescription;
	private String reimbReceipt;
	private String reimbAuthor;
	private String reimbResolver;
	private String reimbStatus;
	public String getSubmittedString() {
		return submittedString;
	}
	public String getResolvedString() {
		return resolvedString;
	}
	private String reimbType;
	public int getReimbID() {
		return reimbID;
	}
	public void setReimbID(int reimbID) {
		this.reimbID = reimbID;
	}
	public double getReimbAmount() {
		return reimbAmount;
	}
	public void setReimbAmount(double reimbAmount) {
		this.reimbAmount = reimbAmount;
	}
	public Date getReimbSubmitted() {
		return reimbSubmitted;
	}
	public void setReimbSubmitted(Date reimbSubmitted) {
		this.reimbSubmitted = reimbSubmitted;
		this.submittedString = reimbSubmitted.toString();
	}
	public Date getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
		if(reimbResolved != null) {
		this.resolvedString = reimbResolved.toString();
		}
	}
	public String getReimbDescription() {
		return reimbDescription;
	}
	public void setReimbDescription(String reimbDescription) {
		this.reimbDescription = reimbDescription;
	}
	public String getReimbReceipt() {
		return reimbReceipt;
	}
	public void setReimbReceipt(String reimbReceipt) {
		this.reimbReceipt = reimbReceipt;
	}
	public String getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(String reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public String getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(String reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public String getReimbStatus() {
		return reimbStatus;
	}
	public void setReimbStatus(String reimbStatus) {
		this.reimbStatus = reimbStatus;
	}
	public String getReimbType() {
		return reimbType;
	}
	public void setReimbType(String reimbType) {
		this.reimbType = reimbType;
	}
	
	
}
