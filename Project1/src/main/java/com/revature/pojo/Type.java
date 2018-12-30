package com.revature.pojo;

public class Type {

	private int typeID;
	private String name;
	
	public Type() {}

	public Type(int typeID, String name) {
		super();
		this.typeID = typeID;
		this.name = name;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Type [typeID=" + typeID + ", name=" + name + "]";
	}
	
	
}
