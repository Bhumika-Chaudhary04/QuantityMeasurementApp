package com.apps.quantitymeasurement.controller;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementController {

	private final IQuantityMeasurementService service;
	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementController(IQuantityMeasurementService service,
			IQuantityMeasurementRepository repository) {
		this.service = service;
		this.repository = repository;
	}

	public <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U unit) {
		return service.add(q1, q2);
	}

	public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2, U unit) {
		return service.subtract(q1, q2);
	}

	public <U extends IMeasurable> double divide(Quantity<U> q1, Quantity<U> q2) {
		return service.divide(q1, q2);
	}
}