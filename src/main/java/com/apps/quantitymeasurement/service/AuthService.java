package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.AuthResponseDTO;
import com.apps.quantitymeasurement.dto.LoginRequestDTO;
import com.apps.quantitymeasurement.dto.RegisterRequestDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

	AuthResponseDTO register(RegisterRequestDTO dto);

	AuthResponseDTO login(LoginRequestDTO dto);

	String logout(HttpServletRequest request);
}