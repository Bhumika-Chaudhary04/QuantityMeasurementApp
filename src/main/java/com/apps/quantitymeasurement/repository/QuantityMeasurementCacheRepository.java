package com.apps.quantitymeasurement.repository;

import java.util.HashSet;
import java.util.Set;

import com.apps.quantitymeasurement.entity.Quantity;

public class QuantityMeasurementCacheRepository {

	private final Set<Quantity<?>> history = new HashSet<>();

	public void save(Quantity<?> quantity) {
		history.add(quantity);
	}

	public Set<Quantity<?>> findAll() {
		return history;
	}
}