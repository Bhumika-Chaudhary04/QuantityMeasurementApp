package com.apps.quantitymeasurement.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionPool {

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(ApplicationConfig.getUrl(), ApplicationConfig.getUser(),
					ApplicationConfig.getPassword());
		} catch (Exception e) {
			throw new RuntimeException("Connection failed", e);
		}
	}
}