package com.apps.quantitymeasurement.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConfig {

	private static final String URL = "jdbc:h2:~/quantity_db";
	private static final String USER = "sa";
	private static final String PASSWORD = "";

	public static Connection getConnection() {
		try {
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Database connected successfully");
			return connection;
		} catch (Exception e) {
			throw new RuntimeException("DB connection failed", e);
		}
	}
}