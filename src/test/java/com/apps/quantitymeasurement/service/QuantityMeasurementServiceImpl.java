package com.apps.quantitymeasurement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import com.apps.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.units.IMeasurable;
import com.apps.quantitymeasurement.utils.QuantityConverter;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	@Autowired
	private QuantityMeasurementRepository repository;

	private void validateSameType(Quantity<? extends IMeasurable> q1, Quantity<? extends IMeasurable> q2) {

		if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
			throw new IllegalArgumentException("Cannot operate on different measurement types");
		}
	}

	@Override
	public QuantityMeasurementDTO add(QuantityInputDTO input) {
		try {
			Quantity<? extends IMeasurable> q1 = QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<? extends IMeasurable> q2 = QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			double result = ((Quantity<IMeasurable>) q1).add((Quantity<IMeasurable>) q2, (IMeasurable) q1.getUnit())
					.getValue();

			repository.save(new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), result));

			return new QuantityMeasurementDTO("ADD", result);

		} catch (Exception e) {
			repository.save(new QuantityMeasurementEntity("ADD", e.getMessage()));
			return new QuantityMeasurementDTO("ADD", e.getMessage());
		}
	}

	@Override
	public QuantityMeasurementDTO convert(QuantityInputDTO input) {
		try {
			Quantity<? extends IMeasurable> q1 = QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<? extends IMeasurable> target = QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, target);

			double result = ((Quantity<IMeasurable>) q1).convertTo((IMeasurable) target.getUnit()).getValue();

			repository.save(new QuantityMeasurementEntity("CONVERT", q1.getValue(), 0, result));

			return new QuantityMeasurementDTO("CONVERT", result);

		} catch (Exception e) {
			return new QuantityMeasurementDTO("CONVERT", e.getMessage());
		}
	}

	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input) {
		try {
			Quantity<? extends IMeasurable> q1 = QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<? extends IMeasurable> q2 = QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			boolean result = q1.equals(q2);

			repository.save(new QuantityMeasurementEntity("COMPARE", q1.getValue(), q2.getValue(), result ? 1 : 0));

			return new QuantityMeasurementDTO("COMPARE", result ? 1.0 : 0.0);

		} catch (Exception e) {
			return new QuantityMeasurementDTO("COMPARE", e.getMessage());
		}
	}

	@Override
	public List<?> getHistory() {
		return repository.findAll();
	}
}