package com.revature.pojo;

public class Status {
	private int statusID;
	private String name;
	
	public Status() {}

	public Status(int statusID, String name) {
		super();
		this.statusID = statusID;
		this.name = name;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Status [statusID=" + statusID + ", name=" + name + "]";
	}
	
	
}
