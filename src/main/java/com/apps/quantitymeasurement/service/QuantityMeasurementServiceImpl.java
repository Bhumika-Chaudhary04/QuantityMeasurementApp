package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final IQuantityMeasurementRepository repository;

	public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
		this.repository = repository;
	}

	@Override
	public <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {

		Quantity<U> result = q1.add(q2, q1.getUnit());

		repository.save(q1.getUnit().getClass().getSimpleName(), "ADD", q1.getValue(), ((Enum<?>) q1.getUnit()).name(),
				q2.getValue(), ((Enum<?>) q2.getUnit()).name(), result.getValue(), ((Enum<?>) result.getUnit()).name());

		return result;
	}

	@Override
	public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {

		Quantity<U> result = q1.subtract(q2, q1.getUnit());

		repository.save(q1.getUnit().getClass().getSimpleName(), "SUBTRACT", q1.getValue(),
				((Enum<?>) q1.getUnit()).name(), q2.getValue(), ((Enum<?>) q2.getUnit()).name(), result.getValue(),
				((Enum<?>) result.getUnit()).name());

		return result;
	}

	@Override
	public <U extends IMeasurable> double divide(Quantity<U> q1, Quantity<U> q2) {
		return q1.divide(q2);
	}
}