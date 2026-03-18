package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

	private static QuantityMeasurementCacheRepository instance;

	private final List<QuantityMeasurementEntity> cache = new ArrayList<>();

	private QuantityMeasurementCacheRepository() {
	}

	public static QuantityMeasurementCacheRepository getInstance() {
		if (instance == null) {
			instance = new QuantityMeasurementCacheRepository();
		}
		return instance;
	}

	@Override
	public void save(QuantityMeasurementEntity entity) {
		if (entity != null) {
			cache.add(entity);
		}
	}

	@Override
	public List<QuantityMeasurementEntity> getAll() {
		return new ArrayList<>(cache);
	}

	@Override
	public int count() {
		return cache.size();
	}

	@Override
	public void deleteAll() {
		cache.clear();
	}
}