package org.data.model;

import java.sql.Connection;

public class Theme {
	private int id;
	private String name;
	
	public Theme(){
		
	}
	
	public Theme(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(":");
		sb.append(name);
		
		return sb.toString();
	}
	
	
}
