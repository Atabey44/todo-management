package com.atabey.todo_management.controller;

import com.atabey.todo_management.dto.JwtAuthResponse;
import com.atabey.todo_management.dto.LoginDto;
import com.atabey.todo_management.dto.RegisterDto;
import com.atabey.todo_management.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String > register(@RequestBody RegisterDto registerDto){
      String response =   authService.register(registerDto);

      return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
    String token =  authService.login(loginDto);

    JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
    jwtAuthResponse.setAccessToken(token);
    return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

}
