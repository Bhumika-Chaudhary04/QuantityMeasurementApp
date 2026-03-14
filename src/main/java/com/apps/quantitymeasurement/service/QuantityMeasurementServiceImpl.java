package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	@Override
	public double add(QuantityDTO q1, QuantityDTO q2) {

		double result = q1.getValue() + q2.getValue();

		repository.save(new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), result));

		return result;
	}

	@Override
	public double subtract(QuantityDTO q1, QuantityDTO q2) {

		double result = q1.getValue() - q2.getValue();

		repository.save(new QuantityMeasurementEntity("SUBTRACT", q1.getValue(), q2.getValue(), result));

		return result;
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {

		if (q2.getValue() == 0) {
			throw new ArithmeticException("Cannot divide by zero");
		}

		double result = q1.getValue() / q2.getValue();

		repository.save(new QuantityMeasurementEntity("DIVIDE", q1.getValue(), q2.getValue(), result));

		return result;
	}
}