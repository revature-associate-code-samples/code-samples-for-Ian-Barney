package com.revature.pojo;

import java.awt.Image;
import java.sql.Date;

public class Reimbursement {
	
	private int reimbID;
	private double reimbAmount;
	private Date reimbSubmitted;
	private Date reimbResolved;
	private String reimbDescription;
	private String reimbReceipt;
	private int reimbAuthor;
	private int reimbResolver;
	private int reimbStatusID;
	private int reimbTypeID;
	
	public Reimbursement() {}
	
	public Reimbursement(int reimbID, double reimbAmount, Date reimbSubmitted, Date reimbResolved,
			String reimbDescription, String reimbReceipt, int reimbAuthor, int reimbResolver, int reimbStatusID,
			int reimbTypeID) {
		super();
		this.reimbID = reimbID;
		this.reimbAmount = reimbAmount;
		this.reimbSubmitted = reimbSubmitted;
		this.reimbResolved = reimbResolved;
		this.reimbDescription = reimbDescription;
		this.reimbReceipt = reimbReceipt;
		this.reimbAuthor = reimbAuthor;
		this.reimbResolver = reimbResolver;
		this.reimbStatusID = reimbStatusID;
		this.reimbTypeID = reimbTypeID;
	}
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
	}
	public Date getReimbResolved() {
		return reimbResolved;
	}
	public void setReimbResolved(Date reimbResolved) {
		this.reimbResolved = reimbResolved;
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
	public int getReimbAuthor() {
		return reimbAuthor;
	}
	public void setReimbAuthor(int reimbAuthor) {
		this.reimbAuthor = reimbAuthor;
	}
	public int getReimbResolver() {
		return reimbResolver;
	}
	public void setReimbResolver(int reimbResolver) {
		this.reimbResolver = reimbResolver;
	}
	public int getReimbStatusID() {
		return reimbStatusID;
	}
	public void setReimbStatusID(int reimbStatusID) {
		this.reimbStatusID = reimbStatusID;
	}
	public int getReimbTypeID() {
		return reimbTypeID;
	}
	public void setReimbTypeID(int reimbTypeID) {
		this.reimbTypeID = reimbTypeID;
	}

	@Override
	public String toString() {
		return "ReimbursementPojo [reimbID=" + reimbID + ", reimbAmount=" + reimbAmount + ", reimbSubmitted="
				+ reimbSubmitted + ", reimbResolved=" + reimbResolved + ", reimbDescription=" + reimbDescription
				+ ", reimbReceipt=" + reimbReceipt + ", reimbAuthor=" + reimbAuthor + ", reimbResolver=" + reimbResolver
				+ ", reimbStatusID=" + reimbStatusID + ", reimbTypeID=" + reimbTypeID + "]";
	}
	
	
}
