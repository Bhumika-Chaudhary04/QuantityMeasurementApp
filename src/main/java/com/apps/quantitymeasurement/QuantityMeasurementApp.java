package com.apps.quantitymeasurement;

import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		QuantityMeasurementCacheRepository repository = QuantityMeasurementCacheRepository.getInstance();

		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);

		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		QuantityDTO q1 = new QuantityDTO(10, "METER");
		QuantityDTO q2 = new QuantityDTO(5, "METER");

		controller.performAddition(q1, q2);
		controller.performSubtraction(q1, q2);
		controller.performDivision(q1, q2);
	}
}