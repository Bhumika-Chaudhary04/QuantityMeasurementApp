package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.*;

import java.util.List;

public interface IQuantityMeasurementService {

	QuantityMeasurementDTO add(QuantityInputDTO input);

	QuantityMeasurementDTO subtract(QuantityInputDTO input);

	QuantityMeasurementDTO divide(QuantityInputDTO input);

	QuantityMeasurementDTO convert(QuantityInputDTO input);

	QuantityMeasurementDTO compare(QuantityInputDTO input);

	List<?> getHistory();

	List<?> getByOperation(String operation);

	void deleteAllHistory();

	void deleteHistoryById(Long id);
}