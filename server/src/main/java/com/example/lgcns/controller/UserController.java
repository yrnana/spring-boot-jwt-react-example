package com.example.lgcns.controller;

import java.util.Collections;
import java.util.Optional;

import com.example.lgcns.exception.UserAlreadyExistException;
import com.example.lgcns.model.Role;
import com.example.lgcns.model.User;
import com.example.lgcns.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@PostMapping("/registration")
	public User registration(@RequestBody User input) {

		Optional<User> exist = userRepository.findByUsernameIgnoreCase(input.getUsername());
		if (exist.isPresent()) {
			throw new UserAlreadyExistException("Username is exist.");
		}

		// create user
		input.setPassword(passwordEncoder.encode(input.getPassword()));
		input.setRoles(Collections.singleton(Role.USER));

		return userRepository.save(input);
	}
}