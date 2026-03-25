package com.apps.quantitymeasurement.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quantity_measurements")
public class QuantityMeasurementEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String operation;
	private double operand1;
	private double operand2;
	private double result;
	private boolean error;
	private String errorMessage;

	public QuantityMeasurementEntity() {
	}

	public QuantityMeasurementEntity(String operation, double op1, double op2, double result) {
		this.operation = operation;
		this.operand1 = op1;
		this.operand2 = op2;
		this.result = result;
		this.error = false;
	}

	public QuantityMeasurementEntity(String operation, String errorMessage) {
		this.operation = operation;
		this.error = true;
		this.errorMessage = errorMessage;
	}

	// getters
	public Long getId() {
		return id;
	}

	public String getOperation() {
		return operation;
	}

	public double getResult() {
		return result;
	}

	public boolean isError() {
		return error;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}