package com.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.blog.security.JwtAuthenticationEntryPoint;

@Component
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// disabled csrf token
		http.csrf(c -> c.disable())

				// disabled cors
				.cors(c -> c.disable())

				// authorize all request except /auth/login
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/v1/**").authenticated()
						.requestMatchers("/auth/login").permitAll().anyRequest().authenticated())

				// Handle Exception
				.exceptionHandling(ex -> ex.authenticationEntryPoint(entryPoint))

				// Make session stateless
				.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		// Add Jwt Filter
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

}
