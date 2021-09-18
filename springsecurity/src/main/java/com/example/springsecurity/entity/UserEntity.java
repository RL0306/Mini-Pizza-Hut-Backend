package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 8, message = "Username must contain at least 8 characters")
    private String username;

    @Size(min = 16, message = "Username must contain at least 16 characters")
    @Email
    private String email;

    @Size(min = 7,  message = "Username must contain at least 8 characters")
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<CardEntity> cardEntities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userEntity")
    private List<OrderEntity> orderEntities;

    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = UserRole.ROLE_USER;
        this.cardEntities = new ArrayList<>();
        this.orderEntities = new ArrayList<>();
    }


}
