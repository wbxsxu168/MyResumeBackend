package com.sunx.mysprmdbapp1.controllers;

import com.sunx.mysprmdbapp1.reqdto.JwtAuthResponseDto;
import com.sunx.mysprmdbapp1.reqdto.signinAccountDto;
import com.sunx.mysprmdbapp1.reqdto.signupAccountDto;
import com.sunx.mysprmdbapp1.services.JwtAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This is RESTAPI entry point for identity management. 
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private JwtAuthService authService;

	public AuthController(JwtAuthService authService) {
		this.authService = authService;
	}

	// Handling Login REST API logic
	@PostMapping(value = { "/login", "/signin" })
	public ResponseEntity<JwtAuthResponseDto> login(@RequestBody signinAccountDto loginDto) {
		// System.out.println("enter /api/v1/auth/signin");
		String token = authService.login(loginDto);
		JwtAuthResponseDto jwtAuthResponse = new JwtAuthResponseDto();
		jwtAuthResponse.setAccessToken(token);
		return ResponseEntity.ok(jwtAuthResponse);
	}

	// Handling UserAccount SignUp logic
	@PostMapping(value = { "/signup", "/register" })
	public ResponseEntity<String> register(@RequestBody signupAccountDto registerDto) {
		String response = authService.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}