package com.example.springsecurity.dto;

import lombok.Data;

@Data
public class PizzaDTO {
    private String name;
    private String description;
    private double price;
    private String path;
}
