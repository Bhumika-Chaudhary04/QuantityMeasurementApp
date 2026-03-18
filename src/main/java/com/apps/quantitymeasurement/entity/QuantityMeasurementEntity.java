package com.apps.quantitymeasurement.entity;

public class QuantityMeasurementEntity {
	private String measurementType;
	private String operationType;
	private double value1;
	private double value2;
	private boolean result;

	public QuantityMeasurementEntity(String measurementType, String operationType, double value1, double value2,
			boolean result) {
		this.measurementType = measurementType;
		this.operationType = operationType;
		this.value1 = value1;
		this.value2 = value2;
		this.result = result;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public String getOperationType() {
		return operationType;
	}

	public double getValue1() {
		return value1;
	}

	public double getValue2() {
		return value2;
	}

	public boolean isResult() {
		return result;
	}

	@Override
	public String toString() {
		return "Type: " + measurementType + ", Operation: " + operationType + ", Value1: " + value1 + ", Value2: "
				+ value2 + ", Result: " + result;
	}
}