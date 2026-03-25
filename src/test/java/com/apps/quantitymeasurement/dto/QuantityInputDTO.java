package com.apps.quantitymeasurement.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class QuantityInputDTO {

	@NotNull
	@Valid
	private QuantityDTO thisQuantityDTO;

	@NotNull
	@Valid
	private QuantityDTO thatQuantityDTO;

	public QuantityDTO getThisQuantityDTO() {
		return thisQuantityDTO;
	}

	public void setThisQuantityDTO(QuantityDTO thisQuantityDTO) {
		this.thisQuantityDTO = thisQuantityDTO;
	}

	public QuantityDTO getThatQuantityDTO() {
		return thatQuantityDTO;
	}

	public void setThatQuantityDTO(QuantityDTO thatQuantityDTO) {
		this.thatQuantityDTO = thatQuantityDTO;
	}
}