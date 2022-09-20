package org.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.data.jdbc.DatabaseConnection;
import org.data.model.Author;
import org.data.model.Country;

// DAO stands for Data Access Object

public class AuthorDao {
	private Connection conn;
	private CountryDao countryDao;
	
	
	private AuthorDao(Connection conn) {
		this.conn = conn;
		countryDao = new CountryDao(conn);
	}

	public Author findById(int id) throws SQLException {
		Author author = null;

		PreparedStatement pstmt = conn
				.prepareStatement("select id, name, last_name, country_id from author where id = ?");
		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			author = new Author();
			author.setId(rs.getInt(1));
			author.setName(rs.getString(2));
			author.setLastName(rs.getString(3));
			
			int countryId = rs.getInt(4);
			Country country = countryDao.findById(countryId);
			author.setCountry(country);
		}

		return author;
	}
	
	public List<Author> findByNameMatchSafe(String match) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		
		PreparedStatement pstmt = conn.prepareStatement("select id, name, last_name, country_id from author where name like ?");
		pstmt.setString(1, match);
		ResultSet rs = pstmt.executeQuery();
		
		
		while (rs.next()) {
			Author author = new Author();
			author.setId(rs.getInt(1));
			author.setName(rs.getString(2));
			author.setLastName(rs.getString(3));
			
			int countryId = rs.getInt(4);
			Country country = countryDao.findById(countryId);
			author.setCountry(country);
			
			authors.add(author);
		}
		
		
		return authors;
	}

	
	public List<Author> findByNameMatchUnSafe(String match) throws SQLException {
		List<Author> authors = new ArrayList<Author>();
		
		Statement stmt = conn.createStatement();
				
		ResultSet rs = stmt.executeQuery("select id, name, last_name, country_id from author where name like \"%" + match + "%\"");		
		
		while (rs.next()) {
			Author author = new Author();
			author.setId(rs.getInt(1));
			author.setName(rs.getString(2));
			author.setLastName(rs.getString(3));
			
			int countryId = rs.getInt(4);
			Country country = countryDao.findById(countryId);
			author.setCountry(country);
			
			authors.add(author);
		}
		
		
		return authors;
	}
	
	
	public static void main(String[] args) throws SQLException {
		try (Connection con = DatabaseConnection.getDatabaseConnection()) {
			AuthorDao authorDao = new AuthorDao(con);
			Author author = authorDao.findById(5);
			System.out.println(author);
			
			
			
			System.out.println("\nFind name matches %es%");
			List<Author> authors = authorDao.findByNameMatchSafe("%es%");
			for (Author a : authors) {
				System.out.println(a);;
			}
			
			
			System.out.println("\nFind name matches SAFE for `es` \n");
			authors = authorDao.findByNameMatchSafe("\" or 1 = 1 or \"a\" != \"");
			for (Author a : authors) {
				System.out.println(a);;
			}
			
			
			System.out.println("\nFind name matches UNSAFE for `es` \n");
			
			authors = authorDao.findByNameMatchUnSafe("\" or 1 = 1 or \"a\" != \"");
			for (Author a : authors) {
				System.out.println(a);;
			}
			
		}
	}	
}
