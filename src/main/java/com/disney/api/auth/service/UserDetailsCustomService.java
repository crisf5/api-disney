package com.disney.api.auth.service;

import com.disney.api.auth.config.SecurityConfiguration;
import com.disney.api.auth.dto.AuthenticationRequest;
import com.disney.api.auth.dto.UserDTO;
import com.disney.api.auth.entity.UserEntity;
import com.disney.api.auth.repository.UserRepository;
import com.disney.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found.");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean createUser(UserDTO userDTO)  {

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(securityConfiguration.passwordEncoder().encode(userDTO.getPassword()));
        userEntity = userRepository.save(userEntity);

        if (userEntity != null) {
            emailService.SendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }

    public UserDetails loginUser(AuthenticationRequest authRequest) throws Exception{

        UserDetails userDetails;

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            userDetails = (UserDetails) auth.getPrincipal();
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        return userDetails;

    }

}
