package com.example.springsecurity.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class RegisterRequestDTO {
    private String username;
    private String email;
    private String password;
}
