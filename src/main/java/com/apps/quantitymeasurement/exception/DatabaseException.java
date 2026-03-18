package com.apps.quantitymeasurement.exception;

public class DatabaseException extends RuntimeException {
	public DatabaseException(String msg, Throwable cause) {
		super(msg, cause);
	}
}