package com.example.authservice.service;

import com.example.authservice.auth.JwtUtils;
import com.example.authservice.domain.User;
import com.example.authservice.domain.UserDetailCus;
import com.example.authservice.factory.AuthResponse;
import com.example.authservice.factory.RequestRegister;
import com.example.authservice.factory.Role;
import com.example.authservice.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JwtService {


    @Autowired
    private UserDetailServiceCus detailServiceCustom;


    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private PasswordEncoder encoder;

    public AuthResponse authenticate(RequestRegister request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailCus userPrincipal = (UserDetailCus) authentication.getPrincipal();
        String token = jwtUtils.generateJwt(userPrincipal.getUsername());
        return new AuthResponse(token);
    }

    public String register(RequestRegister register) throws UsernameNotFoundException {

//        boolean isExistEmail = (userRepository.findUserByUsername(register.getUsername())) != null;
//
//        if (isExistEmail) {
//            throw new UsernameNotFoundException("Email " + register.getUsername() + " is already registered");
//        }

        String passwordEncoder = encoder.encode(register.getPassword());
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(passwordEncoder);
        user.setCreatedAt(LocalDateTime.now());
//        user.setEnabled(true);

        user.setRole(Role.USER);
        userRepository.save(user);

        return "Register sucessfully !";
    }


}
