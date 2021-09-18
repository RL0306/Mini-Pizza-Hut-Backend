package com.example.springsecurity.service;

import com.example.springsecurity.dto.RegisterRequestDTO;
import com.example.springsecurity.security.PasswordEncoder;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;


    public UserDTO register(RegisterRequestDTO registerRequestDTO) {
        Optional<UserEntity> checkForCurrentUser = userRepository.findByUsername(registerRequestDTO.getUsername());

        if(checkForCurrentUser.isPresent()){
            throw new IllegalStateException("Username already exists, try another!");
        }
        UserEntity userEntity = new UserEntity(registerRequestDTO.getUsername(), registerRequestDTO.getEmail(), passwordEncoder.bCryptPasswordEncoder().encode(registerRequestDTO.getPassword()));
        userRepository.save(userEntity);

        //why error here
        return mapper.convertValue(userEntity, UserDTO.class);
    }

}
