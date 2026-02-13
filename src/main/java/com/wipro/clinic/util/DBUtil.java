package com.wipro.clinic.util;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	public static Connection getDBConnection() {
	    try {
	        Class.forName("oracle.jdbc.OracleDriver");
	        String url = "jdbc:oracle:thin:@localhost:1521:XE";
	        String user="system";
	        String pass="Bharath2006";
	        Connection connection=DriverManager.getConnection(url,user,pass);
	        return connection;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}
}