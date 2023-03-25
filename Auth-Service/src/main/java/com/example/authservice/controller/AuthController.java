package com.example.authservice.controller;

import com.example.authservice.factory.RequestRegister;
import com.example.authservice.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RequestRegister request) {
        return ResponseEntity.ok(jwtService.register(request));
    }


    @PostMapping("/login")
    public ResponseEntity<?> singin(@RequestBody RequestRegister request) {
        return ResponseEntity.ok(jwtService.authenticate(request));
    }


}
