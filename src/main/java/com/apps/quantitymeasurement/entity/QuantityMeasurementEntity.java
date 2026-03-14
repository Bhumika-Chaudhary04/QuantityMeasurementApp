package com.apps.quantitymeasurement.entity;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

	private String operation;
	private double operand1;
	private double operand2;
	private double result;

	public QuantityMeasurementEntity(String operation, double operand1, double operand2, double result) {

		this.operation = operation;
		this.operand1 = operand1;
		this.operand2 = operand2;
		this.result = result;
	}

	@Override
	public String toString() {
		return operation + " : " + operand1 + " , " + operand2 + " = " + result;
	}
}