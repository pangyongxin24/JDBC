package org.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.data.jdbc.DatabaseConnection;
import org.data.model.Book;
import org.data.model.Theme;

public class ThemeDao {
	private Connection conn;
	
	
	public ThemeDao(Connection conn) {
		this.conn = conn;
	}
	
	public int createTheme(int id, String name) throws SQLException{
		PreparedStatement pstmt = conn.prepareStatement("insert into theme(id, name) values(?,?)");
		pstmt.setInt(1, id);
		pstmt.setString(2, name);
		int rs = pstmt.executeUpdate();
		return rs;
	}

	public Theme findById(int themeId) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement("select id, name from theme where id = ?");
		Theme theme = null;
		
		pstmt.setInt(1, themeId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next()) {
			theme = new Theme();
			theme.setId(rs.getInt(1));
			theme.setName(rs.getString(2));
		}	
		return theme;
	}
	
	public List<Theme> findByName(String match) throws SQLException {
		List<Theme> themes = new ArrayList<Theme>();
		
		PreparedStatement pstmt = conn.prepareStatement("select id, name from theme where name like ?");
		pstmt.setString(1, match);
		ResultSet rs = pstmt.executeQuery();
		
		
		while (rs.next()) {
			Theme theme = new Theme();
			theme.setId(rs.getInt(1));
			theme.setName(rs.getString(2));
	
			themes.add(theme);
		}	
		return themes;
	}
	public static void main(String[] args) throws SQLException {
		try(Connection con = DatabaseConnection.getDatabaseConnection()){
			ThemeDao themeDao = new ThemeDao(con);
			Theme theme = themeDao.findById(2);
			System.out.println(theme);
/**			
			System.out.println("\nFind name matches Romantic");
			List<Theme> themes = themeDao.findByName("Romantic");
			for (Theme t : themes) {
				System.out.println(t);;
			}
*/			
			themeDao.createTheme(6, "Technology");
			
			System.out.println("\nFind name matches Technology");
			List<Theme> themes = themeDao.findByName("Technology");
			for (Theme t : themes) {
				System.out.println(t);
			}
		}
	}

}
