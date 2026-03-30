package com.apps.quantitymeasurement.security;

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
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.apps.quantitymeasurement.repository.UserRepository;

@Configuration
public class SecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserRepository userRepo;
	private final TokenBlacklist tokenBlacklist;
	private final OAuth2SuccessHandler oAuth2SuccessHandler;

	public SecurityConfig(JwtUtil jwtUtil, UserRepository userRepo, TokenBlacklist tokenBlacklist,
			OAuth2SuccessHandler oAuth2SuccessHandler) {
		this.jwtUtil = jwtUtil;
		this.userRepo = userRepo;
		this.tokenBlacklist = tokenBlacklist;
		this.oAuth2SuccessHandler = oAuth2SuccessHandler;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.cors(cors -> cors.configurationSource(corsConfigurationSource())).csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frame -> frame.disable()))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
				.authorizeHttpRequests(
						auth -> auth
								.requestMatchers("/", "/auth/**", "/oauth2/**", "/login/**", "/login-success",
										"/swagger-ui/**", "/v3/api-docs/**", "/h2-console/**")
								.permitAll().anyRequest().authenticated())
				.oauth2Login(oauth -> oauth.successHandler(oAuth2SuccessHandler)).httpBasic(Customizer.withDefaults())
				.addFilterBefore(new JwtAuthenticationFilter(jwtUtil, userRepo, tokenBlacklist),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UrlBasedCorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:5173");
		config.addAllowedOrigin("http://127.0.0.1:5173");
		config.addAllowedOrigin("http://localhost:4200");
		config.addAllowedOrigin("http://127.0.0.1:4200");

		config.addAllowedHeader("*");
		config.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}