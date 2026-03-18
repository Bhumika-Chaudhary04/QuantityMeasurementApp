package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.List;

public interface IQuantityMeasurementService {

	double add(QuantityDTO q1, QuantityDTO q2);

	double subtract(QuantityDTO q1, QuantityDTO q2);

	double divide(QuantityDTO q1, QuantityDTO q2);

	List<QuantityMeasurementEntity> getAll();

	int count();

	void deleteAll();
}