package com.apps.quantitymeasurement.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apps.quantitymeasurement.dto.AuthResponseDTO;
import com.apps.quantitymeasurement.dto.LoginRequestDTO;
import com.apps.quantitymeasurement.dto.RegisterRequestDTO;
import com.apps.quantitymeasurement.entity.AuthProvider;
import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.repository.UserRepository;
import com.apps.quantitymeasurement.security.JwtUtil;
import com.apps.quantitymeasurement.security.TokenBlacklist;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository repo;
	private final PasswordEncoder encoder;
	private final JwtUtil jwt;
	private final TokenBlacklist tokenBlacklist;

	public AuthServiceImpl(UserRepository repo, PasswordEncoder encoder, JwtUtil jwt, TokenBlacklist tokenBlacklist) {
		this.repo = repo;
		this.encoder = encoder;
		this.jwt = jwt;
		this.tokenBlacklist = tokenBlacklist;
	}

	@Override
	public AuthResponseDTO register(RegisterRequestDTO dto) {
		if (repo.findByEmail(dto.getEmail()).isPresent()) {
			throw new RuntimeException("User already exists");
		}

		User user = new User();
		user.setEmail(dto.getEmail());
		user.setName(dto.getName());
		user.setPassword(encoder.encode(dto.getPassword()));
		user.setProvider(AuthProvider.LOCAL);

		repo.save(user);

		String token = jwt.generateToken(user.getEmail());
		return new AuthResponseDTO(user.getName(), user.getEmail(), token);
	}

	@Override
	public AuthResponseDTO login(LoginRequestDTO dto) {
		User user = repo.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("Invalid credentials"));

		if (user.getProvider() != AuthProvider.LOCAL) {
			throw new RuntimeException("Please login using Google");
		}

		if (user.getPassword() == null || !encoder.matches(dto.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid credentials");
		}

		String token = jwt.generateToken(user.getEmail());
		return new AuthResponseDTO(user.getName(), user.getEmail(), token);
	}

	@Override
	public String logout(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			String token = authHeader.substring(7);
			tokenBlacklist.blacklistToken(token);
		}

		return "Logged out successfully";
	}
}