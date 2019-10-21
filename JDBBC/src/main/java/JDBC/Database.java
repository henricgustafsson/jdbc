package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	
	
	private static String url ="jdbc:mysql://localhost:3306/world?&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	private static String user = "root";
	private static String pass = "root";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,user,pass);
	}
	
}
