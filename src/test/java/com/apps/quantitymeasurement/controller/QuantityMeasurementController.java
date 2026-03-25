 package com.apps.quantitymeasurement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.apps.quantitymeasurement.dto.*;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/quantities")
public class QuantityMeasurementController {

	@Autowired
	private IQuantityMeasurementService service;

	@PostMapping("/add")
	public QuantityMeasurementDTO add(@Valid @RequestBody QuantityInputDTO input) {
		return service.add(input);
	}

	@PostMapping("/convert")
	public QuantityMeasurementDTO convert(@Valid @RequestBody QuantityInputDTO input) {
		return service.convert(input);
	}

	@PostMapping("/compare")
	public QuantityMeasurementDTO compare(@Valid @RequestBody QuantityInputDTO input) {
		return service.compare(input);
	}

	@GetMapping("/history")
	public List<?> history() {
		return service.getHistory();
	}
}