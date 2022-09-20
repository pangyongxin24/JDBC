package org.data.model;

public class Book {
	private int id;
	private String name;
	private Theme theme;
	
	public Book() {

	}
	
	public Book(int id, String name, Theme theme) {
		this.id = id;
		this.name = name;
		this.theme = theme;
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

	public Theme getTheme() {
		return theme;
	}

	public void setTheme(Theme theme) {
		this.theme = theme;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(":");
		sb.append(name);
		sb.append(":[");
		sb.append(theme);
		sb.append("]");
		return sb.toString();
	}
	
	
	
}
