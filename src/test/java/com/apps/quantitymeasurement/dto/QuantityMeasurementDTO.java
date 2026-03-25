package com.apps.quantitymeasurement.dto;

public class QuantityMeasurementDTO {

	private String operation;
	private Double result;
	private String message;
	private boolean error;

	public QuantityMeasurementDTO() {
	}

	public QuantityMeasurementDTO(String operation, Double result) {
		this.operation = operation;
		this.result = result;
		this.error = false;
	}

	public QuantityMeasurementDTO(String operation, String message) {
		this.operation = operation;
		this.message = message;
		this.error = true;
	}

	public String getOperation() {
		return operation;
	}

	public Double getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public boolean isError() {
		return error;
	}
}