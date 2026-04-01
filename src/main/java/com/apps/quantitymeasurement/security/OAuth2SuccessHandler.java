package com.apps.quantitymeasurement.security;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${app.frontend-url}")
	private String frontendUrl;

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

		clearAuthenticationAttributes(request);

		String redirectUrl = frontendUrl + "/?token="
				+ URLEncoder.encode(token, StandardCharsets.UTF_8)
				+ "&name=" + URLEncoder.encode(name, StandardCharsets.UTF_8)
				+ "&email=" + URLEncoder.encode(email, StandardCharsets.UTF_8);

		getRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}
}