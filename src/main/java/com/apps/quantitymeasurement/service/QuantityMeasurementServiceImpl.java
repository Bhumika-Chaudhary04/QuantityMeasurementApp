package com.apps.quantitymeasurement.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.apps.quantitymeasurement.dto.QuantityInputDTO;
import com.apps.quantitymeasurement.dto.QuantityMeasurementDTO;
import com.apps.quantitymeasurement.entity.Quantity;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.repository.QuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.UserRepository;
import com.apps.quantitymeasurement.units.IMeasurable;
import com.apps.quantitymeasurement.utils.QuantityConverter;

@Service
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

	private static final Logger logger = LoggerFactory.getLogger(QuantityMeasurementServiceImpl.class);

	private final QuantityMeasurementRepository repository;
	private final UserRepository userRepository;

	public QuantityMeasurementServiceImpl(QuantityMeasurementRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}

	private User getCurrentUser() {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}

	private void saveHistory(QuantityMeasurementEntity entity) {
		entity.setUser(getCurrentUser());
		repository.save(entity);
	}

	private void validateSameType(Quantity<? extends IMeasurable> q1, Quantity<? extends IMeasurable> q2) {
		if (!q1.getUnit().getClass().equals(q2.getUnit().getClass())) {
			throw new IllegalArgumentException("Cannot operate on different measurement types");
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO add(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			double result = q1.add(q2, q1.getUnit()).getValue();

			saveHistory(new QuantityMeasurementEntity("ADD", q1.getValue(), q2.getValue(), result));

			return new QuantityMeasurementDTO("ADD", result);

		} catch (Exception e) {
			logger.error("ADD failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("ADD", e.getMessage()));
			return new QuantityMeasurementDTO("ADD", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO subtract(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			double result = q1.subtract(q2, q1.getUnit()).getValue();

			saveHistory(new QuantityMeasurementEntity("SUBTRACT", q1.getValue(), q2.getValue(), result));

			return new QuantityMeasurementDTO("SUBTRACT", result);

		} catch (Exception e) {
			logger.error("SUBTRACT failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("SUBTRACT", e.getMessage()));
			return new QuantityMeasurementDTO("SUBTRACT", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Double divide(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			if (q2.getValue() == 0) {
				throw new ArithmeticException("Division by zero");
			}

			double result = q1.divide(q2);

			saveHistory(new QuantityMeasurementEntity("DIVIDE", q1.getValue(), q2.getValue(), result));

			return result;

		} catch (Exception e) {
			logger.error("DIVIDE failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("DIVIDE", e.getMessage()));
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO convert(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> target = (Quantity<IMeasurable>) QuantityConverter
					.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, target);

			double result = q1.convertTo(target.getUnit()).getValue();

			saveHistory(new QuantityMeasurementEntity("CONVERT", q1.getValue(), 0, result));

			return new QuantityMeasurementDTO("CONVERT", result);

		} catch (Exception e) {
			logger.error("CONVERT failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("CONVERT", e.getMessage()));
			return new QuantityMeasurementDTO("CONVERT", e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public QuantityMeasurementDTO compare(QuantityInputDTO input) {
		try {
			Quantity<IMeasurable> q1 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThisQuantityDTO());
			Quantity<IMeasurable> q2 = (Quantity<IMeasurable>) QuantityConverter.toEntity(input.getThatQuantityDTO());

			validateSameType(q1, q2);

			boolean result = q1.equals(q2);

			saveHistory(new QuantityMeasurementEntity("COMPARE", q1.getValue(), q2.getValue(), result ? 1 : 0));

			return new QuantityMeasurementDTO("COMPARE", result ? 1.0 : 0.0);

		} catch (Exception e) {
			logger.error("COMPARE failed: {}", e.getMessage());
			saveHistory(new QuantityMeasurementEntity("COMPARE", e.getMessage()));
			return new QuantityMeasurementDTO("COMPARE", e.getMessage());
		}
	}

	@Override
	public List<?> getHistory() {
		return repository.findByUser(getCurrentUser());
	}

	@Override
	public List<?> getByOperation(String operation) {
		return repository.findByUserAndOperationIgnoreCase(getCurrentUser(), operation);
	}
}