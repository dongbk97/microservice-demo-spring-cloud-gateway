package com.example.authservice.service;

import com.example.authservice.domain.User;
import com.example.authservice.domain.UserDetailCus;
import com.example.authservice.factory.Role;
import com.example.authservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Slf4j
public class UserDetailServiceCus implements UserDetailsService {

    @Autowired
    private UserRepo userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found"));

        if (!user.getEnabled()) {
            try {
                throw new UsernameNotFoundException("Username " + username + "not Enable");
            } catch (UsernameNotFoundException e) {
                log.error(e.getMessage());
            }
        }
        UserDetailCus userDetailCus = new UserDetailCus(user);
        return userDetailCus;
    }






}
