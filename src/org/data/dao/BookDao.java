package org.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.data.jdbc.DatabaseConnection;
import org.data.model.Author;
import org.data.model.Book;
import org.data.model.Country;
import org.data.model.Theme;

public class BookDao {
	private Connection conn;
	private ThemeDao themeDao;
	
	public BookDao(Connection conn) {
		this.conn = conn;
		themeDao = new ThemeDao(conn);
	}
	
	public Book findById(int id) throws SQLException{
		Book book = null;
		PreparedStatement pstmt = conn.prepareStatement("select id, name, theme_id from book where id = ?");
	
		
		pstmt.setInt(1, id);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			book = new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
			
			int themeId = rs.getInt(3);
			Theme theme  = themeDao.findById(themeId);
			book.setTheme(theme);
		}	
		return book;
	}
	
	public List<Book> findByName(String match) throws SQLException {
		List<Book> books = new ArrayList<Book>();
		
		PreparedStatement pstmt = conn.prepareStatement("select id, name, theme_id from book where name like ?");
		pstmt.setString(1, match);
		ResultSet rs = pstmt.executeQuery();
		
		
		while (rs.next()) {
			Book book = new Book();
			book.setId(rs.getInt(1));
			book.setName(rs.getString(2));
	
			int themeId = rs.getInt(3);
			Theme theme = themeDao.findById(themeId);
			book.setTheme(theme);
			books.add(book);
		}	
		return books;
	}
	
	public static void main(String[] args) throws SQLException {
		try(Connection con = DatabaseConnection.getDatabaseConnection()){
			BookDao  bookDao = new BookDao(con);
			Book book = bookDao.findById(23);
			System.out.println(book);
			
			System.out.println("\nFind name matches Aura: ");
			List<Book> books = bookDao.findByName("Aura");
			for (Book b : books) {
				System.out.println(b);;
			}
			
		}
	}
	
}
