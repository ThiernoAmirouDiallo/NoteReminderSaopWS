package com.emiage.s12018.noteReminder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@GetMapping("/tests/encode/{password}")
	public String userDetails(@PathVariable String password) {
		return passwordEncoder.encode(password);
	}
}
