package com.nagp.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.userservice.dto.UserDTO;
import com.nagp.userservice.entity.User;
import com.nagp.userservice.service.UserService;

import io.opentracing.Span;
import io.opentracing.Tracer;

/**
 * This controller class contains rest end points to get user details.
 * 
 * @author santoshkumar02
 *
 */
@RestController
public class UserController {
	@Autowired
	private Tracer tracer;
	
	@Autowired
	private UserService userService;

	/**
	 * This method returns user details of the given user id.
	 * @param id
	 * @return user details
	 */
	@GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
		Span span = tracer.buildSpan("user-service").start();
		UserDTO user = userService.getUserById(id);
		span.setTag("http.status_code", 200);
		span.finish();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	/**
	 * This method creats a new user from given details.
	 * @param user
	 * @return user details of created new user
	 */
	@PostMapping(value = "/user", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@Validated @RequestBody UserDTO user) {
		User entity = userService.createUser(user);
		return new ResponseEntity<>(entity, HttpStatus.CREATED);
	}
}
