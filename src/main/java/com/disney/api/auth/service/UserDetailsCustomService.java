package com.disney.api.auth.service;

import com.disney.api.auth.config.SecurityConfiguration;
import com.disney.api.auth.dto.UserDTO;
import com.disney.api.auth.entity.UserEntity;
import com.disney.api.auth.repository.UserRepository;
import com.disney.api.exception.UserExist;
import com.disney.api.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private PasswordEncoder encoder;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Username or password not found.");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
    }

    public boolean createUser(UserDTO userDTO) throws UserExist {

        if (userRepository.findByUsername(userDTO.getUsername()) != null){
            throw new UserExist();
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(encoder.encode(userDTO.getPassword()));
        userEntity = userRepository.save(userEntity);

        if (userEntity != null) {
            emailService.SendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }
}
