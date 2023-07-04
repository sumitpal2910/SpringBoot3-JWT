package com.blog.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.blog.request.JwtRequest;
import com.blog.response.ErrorResponse;
import com.blog.response.Response;
import com.blog.response.SuccessResponse;
import com.blog.response.custom.JwtResponse;
import com.blog.security.JwtHelper;

@Service
public class AuthService extends BaseService {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Response login(JwtRequest params) {

		try {

			try {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						params.getUsername(), params.getPassword());
				authenticationManager.authenticate(authentication);
			} catch (Exception e) {
				return new ErrorResponse(HttpStatus.UNAUTHORIZED, "Invalid Username or Password");
			}

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(params.getUsername());
			String token = this.jwtHelper.generateToken(userDetails);

			JwtResponse response = new JwtResponse(token, params.getUsername());

			return new SuccessResponse(HttpStatus.OK, "Welcome Back", response);

		} catch (Exception e) {
			logger.info("params", params);
			logger.error(e.getMessage(), e);
			return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

}
