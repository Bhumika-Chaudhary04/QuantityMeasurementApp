package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

import java.util.List;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	@Override
	public double add(QuantityDTO q1, QuantityDTO q2) {
		double result = q1.getValue() + q2.getValue();

		repository.save(new QuantityMeasurementEntity(q1.getUnit(), "ADD", q1.getValue(), q2.getValue(), true));

		return result;
	}

	@Override
	public double subtract(QuantityDTO q1, QuantityDTO q2) {
		double result = q1.getValue() - q2.getValue();

		repository.save(new QuantityMeasurementEntity(q1.getUnit(), "SUBTRACT", q1.getValue(), q2.getValue(), true));

		return result;
	}

	@Override
	public double divide(QuantityDTO q1, QuantityDTO q2) {
		double result = q1.getValue() / q2.getValue();

		repository.save(new QuantityMeasurementEntity(q1.getUnit(), "DIVIDE", q1.getValue(), q2.getValue(), true));

		return result;
	}

	@Override
	public List<QuantityMeasurementEntity> getAll() {
		return repository.getAll();
	}

	@Override
	public int count() {
		return repository.count();
	}

	@Override
	public void deleteAll() {
		repository.deleteAll();
	}
}