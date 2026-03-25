package com.apps.quantitymeasurement.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class QuantityDTO {

	@NotNull(message = "Value cannot be null")
	private Double value;

	@NotBlank(message = "Unit cannot be empty")
	private String unit;

	@NotBlank(message = "Measurement type required")
	private String measurementType;

	public QuantityDTO() {
	}

	public QuantityDTO(Double value, String unit) {
		this.value = value;
		this.unit = unit;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMeasurementType() {
		return measurementType;
	}

	public void setMeasurementType(String measurementType) {
		this.measurementType = measurementType;
	}
}