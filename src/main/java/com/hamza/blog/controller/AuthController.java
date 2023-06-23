package com.hamza.blog.controller;

import com.hamza.blog.payload.LoginDto;
import com.hamza.blog.payload.RegisterDto;
import com.hamza.blog.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // login endpoint rest api

    @PostMapping(value = {"/login","/signin"})
    public ResponseEntity<String> login (@RequestBody LoginDto loginDto) {
      String response = authService.login(loginDto);
      return ResponseEntity.ok(response);
    }

    // Build register rest api

    @PostMapping(value = {"/register","/signup"})
    public ResponseEntity<String> register (@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
