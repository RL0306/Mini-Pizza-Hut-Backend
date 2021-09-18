package com.example.springsecurity.controller;

import com.example.springsecurity.dto.RegisterRequestDTO;
import com.example.springsecurity.service.RegistrationService;
import com.example.springsecurity.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RegisterController {


    private final RegistrationService registrationService;

    @PostMapping("/api/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        UserDTO user = registrationService.register(registerRequestDTO);
        return ResponseEntity.ok(user);
    }
}
