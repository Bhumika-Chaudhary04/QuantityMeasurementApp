package com.apps.quantitymeasurement.util;

import java.io.InputStream;
import java.util.Properties;

public class ApplicationConfig {
	private static Properties props = new Properties();

	static {
		try {
			InputStream input = ApplicationConfig.class.getClassLoader().getResourceAsStream("application.properties");
			props.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Failed to load properties");
		}
	}

	public static String getUrl() {
		return props.getProperty("db.url");
	}

	public static String getUser() {
		return props.getProperty("db.username");
	}

	public static String getPassword() {
		return props.getProperty("db.password");
	}

	public static String getRepoType() {
		return props.getProperty("repository.type");
	}
}