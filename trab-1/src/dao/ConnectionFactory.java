package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private String url = "jdbc:mysql://127.0.0.1:3306/lanchonetedb";
	private String user = "root";
	private String pass = "";
	public Connection getConnection() {
		try {
			//Class.forName(jdbc_driver);
			return DriverManager.getConnection(url,user,pass);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
