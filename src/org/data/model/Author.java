package org.data.model;

public class Author {
	private int id;
	private String name;
	private String lastName;
	private Country country;

	public Author() {

	}

	public Author(int id, String name, String lastName, Country country) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.country = country;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(id);
		sb.append(":");
		sb.append(name);
		sb.append(":");
		sb.append(lastName);
		sb.append(":[");
		sb.append(country);
		sb.append("]");
		
		return sb.toString();
	}
}




