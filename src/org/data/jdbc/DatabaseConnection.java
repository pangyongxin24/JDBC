package org.data.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static Connection connection;
	
	public static Connection getDatabaseConnection() throws SQLException {
		if (connection == null) {
			// Initialize connection here.
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/AUTHOR_BOOK", "root", "abcd1234.");
      
		}

		return connection;
	}

	public static void main(String[] args) throws SQLException {
		Connection con = DatabaseConnection.getDatabaseConnection();

		System.out.println(con.getCatalog());
		
		con.close();
		
	}
	
}
