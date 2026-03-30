package com.apps.quantitymeasurement.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.apps.quantitymeasurement.entity.AuthProvider;
import com.apps.quantitymeasurement.entity.User;
import com.apps.quantitymeasurement.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final UserRepository userRepository;
	private final JwtUtil jwtUtil;

	public OAuth2SuccessHandler(UserRepository userRepository, JwtUtil jwtUtil) {
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User oauthUser = (OAuth2User) authentication.getPrincipal();

		String email = oauthUser.getAttribute("email");
		String name = oauthUser.getAttribute("name");

		userRepository.findByEmail(email).orElseGet(() -> {
			User user = new User();
			user.setEmail(email);
			user.setName(name);
			user.setProvider(AuthProvider.GOOGLE);
			return userRepository.save(user);
		});

		String token = jwtUtil.generateToken(email);

		String redirectUrl = "http://localhost:8080/login-success" + "?token=" + token + "&name=" + name + "&email="
				+ email;

		response.sendRedirect(redirectUrl);
	}
}