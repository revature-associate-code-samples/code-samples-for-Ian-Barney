package com.revature.pojo;

import java.sql.Date;

public class StatusChange {
	private int status;
	private int id;
	private int user;
	private Date date;
	
	public StatusChange() {}
	
	public StatusChange(int status, int id, int user, Date date) {
		super();
		this.status = status;
		this.id = id;
		this.user = user;
		this.date = date;
		
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getUser() {
		return user;
	}

	public void setUser(int user) {
		this.user = user;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StatusChange [status=" + status + ", id=" + id + "]";
	}
}
