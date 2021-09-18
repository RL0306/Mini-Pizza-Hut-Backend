package com.example.springsecurity.dto;

import com.example.springsecurity.entity.UserRole;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password;
    private UserRole role;

    private List<CardDTO> cardEntities;

    private List<OrderDTO> orderEntities;


}
