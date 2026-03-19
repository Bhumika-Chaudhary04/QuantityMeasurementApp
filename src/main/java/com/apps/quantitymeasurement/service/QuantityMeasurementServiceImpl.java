package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.units.IMeasurable;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private final QuantityMeasurementCacheRepository repository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementCacheRepository repository) {
		this.repository = repository;
	}

	@Override
	public <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2) {

		Quantity<U> result = q1.add(q2, q1.getUnit());

		repository.save(result);

		return result;
	}

	@Override
	public <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2) {

		Quantity<U> result = q1.subtract(q2, q1.getUnit());

		repository.save(result);

		return result;
	}

	@Override
	public <U extends IMeasurable> double divide(Quantity<U> q1, Quantity<U> q2) {

		return q1.divide(q2);
	}
}