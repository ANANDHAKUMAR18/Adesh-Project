package com.sma.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private DBConnection() {}
	
	private static Connection connection = null;
	
	public static Connection getConnection() {
		
		if(connection == null) {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sma_final","root","");
			}catch(ClassNotFoundException | SQLException e) {
				System.out.println("Something went wrong... try again");
			}
		}
		return connection;
		
	}
	
}
