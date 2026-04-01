package com.apps.quantitymeasurement.security;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apps.quantitymeasurement.repository.UserRepository;

@Configuration
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;
	private final TokenBlacklist tokenBlacklist;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	@Value("${app.allowed-origins}")
	private String allowedOrigins;

	public SecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, TokenBlacklist tokenBlacklist,
			OAuth2SuccessHandler oAuth2SuccessHandler) {
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
		this.tokenBlacklist = tokenBlacklist;
		this.oAuth2SuccessHandler = oAuth2SuccessHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http
			.cors(Customizer.withDefaults())
			.csrf(csrf -> csrf.disable())
			.headers(headers -> headers.frameOptions(frame -> frame.disable()))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(
							"/",
							"/auth/**",
							"/oauth2/**",
							"/login/**",
							"/login-success",
							"/swagger-ui/**",
							"/v3/api-docs/**",
							"/h2-console/**")
					.permitAll()
					.anyRequest().authenticated())
			.oauth2Login(oauth -> oauth.successHandler(oAuth2SuccessHandler))
			.httpBasic(Customizer.withDefaults())
			.addFilterBefore(
					new JwtAuthenticationFilter(jwtUtil, userRepo, tokenBlacklist),
					UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		List<String> origins = Arrays.stream(allowedOrigins.split(","))
				.map(String::trim)
				.filter(origin -> !origin.isEmpty())
				.collect(Collectors.toList());

		config.setAllowedOrigins(origins);
		config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		config.setAllowedHeaders(List.of("*"));
		config.setExposedHeaders(List.of("Authorization"));
		config.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}