package com.blog.controllers;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.request.JwtRequest;
import com.blog.response.Response;
import com.blog.security.JwtHelper;
import com.blog.services.AuthService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;

	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody JwtRequest req) {
//		System.out.println(req);
		Response login = authService.login(req);
		return ResponseEntity.status(login.getStatus()).body(login);

//		boolean doAuthencated = this.doAuthencated(req.getUsername(), req.getPassword());
//		
////		if(!doAuthencated)  
//
//		UserDetails userDetails = userDetailsService.loadUserByUsername(req.getUsername());
//		String token = this.jwtHelper.generateToken(userDetails);
//
//		Map<String, Object> response = new HashMap<>();
//		response.put("token", token);
//		response.put("username", req.getUsername());
//
//		return ResponseEntity.ok(response);
	}

}