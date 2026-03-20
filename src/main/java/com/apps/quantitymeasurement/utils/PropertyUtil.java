package com.apps.quantitymeasurement.utils;

import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

	public static Properties loadProperties() {
		Properties properties = new Properties();

		try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("application.properties")) {

			properties.load(input);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return properties;
	}
}