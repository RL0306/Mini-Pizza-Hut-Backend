package com.example.springsecurity.controller;

import com.example.springsecurity.dto.LoginRequestDTO;
import com.example.springsecurity.service.LoginService;
import com.example.springsecurity.dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class LoginController {


    private final LoginService loginService;

    @PostMapping("/api/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        UserDTO user = loginService.login(loginRequestDTO);
        return ResponseEntity.ok(user);
    }

}
