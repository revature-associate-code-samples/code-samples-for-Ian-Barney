package com.revature.pojos;

public class Account {
	private int accountID;

	private int typeID;
	private double balance;
	private int userID;
	
	public Account() {}

	public Account(int accountID, int typeID, double balance, int userID) {
		super();
		this.accountID = accountID;
		this.typeID = typeID;
		this.balance = balance;
		this.userID = userID;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", typeID=" + typeID + ", balance=" + balance + ", userID=" + userID
				+ "]";
	}
}
