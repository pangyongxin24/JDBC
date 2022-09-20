package org.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.data.jdbc.DatabaseConnection;
import org.data.model.Country;

public class CountryDao {
	private Connection conn;
	
	public CountryDao(Connection conn) {
		this.conn = conn;
	}
	
	public Country findById(int id) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("select id, name from country where id = ?");
		Country country = null;
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			country = new Country();
			country.setId(rs.getInt(1));
			country.setName(rs.getString(2));
		}
		
		return country;
	}
	
	public List<Country> findByName(String name) throws SQLException {
		List<Country> countries = new ArrayList<Country>();
		
		PreparedStatement pstmt = conn.prepareStatement("select id, name from country where name like ?");
		pstmt.setString(1, name);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) {
			Country country = new Country();
			country.setId(rs.getInt(1));
			country.setName(rs.getString(2));
			
			countries.add(country);
		}
		
		return countries;
	}
	
	public static void main(String[] args) throws SQLException {
		try(Connection con = DatabaseConnection.getDatabaseConnection()){
			CountryDao countryDao = new CountryDao(con);
			Country country = countryDao.findById(1);
			System.out.println(country);
			
			System.out.println("Find name: ");
			List<Country> countries = countryDao.findByName("France");
			for(Country c : countries) {
				System.out.println(c);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
