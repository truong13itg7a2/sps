package edu.txts.sps131025.controller;

package com.example.demo.controller;

import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.response.ApiResponse;
import com.example.demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ApiResponse<?> login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}

	@PostMapping("/register")
	public ApiResponse<?> register(@RequestBody LoginRequest request) {
		return authService.register(request);
	}
}

