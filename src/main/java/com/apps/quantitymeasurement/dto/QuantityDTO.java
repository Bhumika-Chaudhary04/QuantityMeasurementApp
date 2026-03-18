package com.apps.quantitymeasurement.dto;

public class QuantityDTO {

	private String unit;
	private double value;

	public QuantityDTO(String unit, double value) {
		this.unit = unit;
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public double getValue() {
		return value;
	}
}