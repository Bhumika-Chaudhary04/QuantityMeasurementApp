package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

public class QuantityMeasurementController {

	private IQuantityMeasurementService service;

	public QuantityMeasurementController(IQuantityMeasurementService service) {
		this.service = service;
	}

	public void performAddition(QuantityDTO q1, QuantityDTO q2) {

		double result = service.add(q1, q2);

		System.out.println("Addition Result = " + result);
	}

	public void performSubtraction(QuantityDTO q1, QuantityDTO q2) {

		double result = service.subtract(q1, q2);

		System.out.println("Subtraction Result = " + result);
	}

	public void performDivision(QuantityDTO q1, QuantityDTO q2) {

		double result = service.divide(q1, q2);

		System.out.println("Division Result = " + result);
	}
}