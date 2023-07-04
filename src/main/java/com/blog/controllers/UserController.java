package com.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.blog.entities.User;
import com.blog.response.Response;
import com.blog.services.UserService;

@RestController
public class UserController extends BaseController {

	private UserService service;

	@Autowired
	public UserController(UserService service) {
		super();
		this.service = service;
	}

	@GetMapping("users")
	public ResponseEntity<?> findAllData() {
		Response response =this.service.findAllData();
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@GetMapping("users/{id}")
	public ResponseEntity<?> findOneData(@PathVariable Long id) {
		Response response = service.findOneData(id);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@PostMapping("users")
	public ResponseEntity<?> addData(@RequestBody User user) {
		Response response = service.addData(user);
		return ResponseEntity.status(response.getStatus()).body(response);
	}

	@PutMapping("users/{id}")
	public ResponseEntity<?> editData(@RequestBody User user, @PathVariable Long id) {
		user.setId(id);
		Response response = service.editData(user);
		return ResponseEntity.status(response.getStatus()).body(response);
	}
}
