package com.gorica.controller.rest;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gorica.dao.model.User;
import com.gorica.service.UserDetailsService;

@RestController
@RequestMapping("/rest/user")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	@Autowired
	private UserDetailsService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/createUser")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		System.out.println("user::" + user);
		ResponseEntity<String> response = null;
		User getUser = userService.getUserByUserName(user.getUserName());
		if (getUser == null) {
			try {
				user.setEnabled(true);
				user.setCreatedDate(new Date());
				user.setPassword(passwordEncoder.encode(user.getPassword()));
				userService.saveUser(user);
				response = new ResponseEntity<>(HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				response = new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
			}
		} else
			response = new ResponseEntity<>("User Name is already Existed with :'" + user.getUserName() + "'",
					HttpStatus.BAD_REQUEST);
		return response;

	}

	@GetMapping("/getAllUsers")
	public ResponseEntity getAllUsers() {
		ResponseEntity response = null;
		try {
			List<User> usersList = userService.getAllUsers();
			response = new ResponseEntity(usersList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@PostMapping("/validateUser")
	public ResponseEntity validateUser(@RequestBody User user) {
		ResponseEntity response = null;
		try {
			User validUser = userService.getUserByUserName(user.getUserName());
			if (validUser != null) {
				if (passwordEncoder.matches(user.getPassword(), validUser.getPassword()))
					response = new ResponseEntity(validUser, HttpStatus.OK);
				else
					response = new ResponseEntity("Incorrect Password", HttpStatus.BAD_REQUEST);
			} else
				response = new ResponseEntity("Incorrect UserName", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}

	@PostMapping("/getUserById")
	public ResponseEntity getUserById(@RequestBody User user) {
		ResponseEntity response = null;
		try {
			User getUser = userService.getUserById(user.getId());
			response = new ResponseEntity(getUser, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ResponseEntity(HttpStatus.EXPECTATION_FAILED);
		}

		return response;
	}
}
