package com.example.springsecurity.service;

import com.example.springsecurity.dto.LoginRequestDTO;
import com.example.springsecurity.exceptions.InvalidCredentialsException;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final ObjectMapper mapper;


    public UserDTO login(LoginRequestDTO loginRequestDTO) {
        UserEntity userEntity = userRepository.findByUsername(loginRequestDTO.getUsername()).orElseThrow(InvalidCredentialsException::new);

        if(loginRequestDTO.getPassword().equals(userEntity.getPassword())){
            return mapper.convertValue(userEntity, UserDTO.class);
        }
        return null;
    }

}
