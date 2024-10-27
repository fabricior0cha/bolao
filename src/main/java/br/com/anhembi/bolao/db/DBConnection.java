package br.com.anhembi.bolao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static Connection conn = null;
	
	public static Connection getConnection()  {
		if(conn == null) {
			try {
				 String url = "jdbc:mysql://localhost:3306/anhembi";
				 String username = "root";
				 String password = "admin";
				 Class.forName("com.mysql.cj.jdbc.Driver");
				 conn = DriverManager.getConnection(url, username, password);
			}
			catch (SQLException e) {
				throw new IllegalStateException("Não foi possível conectar ao banco de dados.", e);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return conn;
	}
}
