package com.example.springsecurity.dto;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrderDTO {

    private PizzaDTO pizzaEntity;

    private CardDTO cardEntity;

    private double price;

    private LocalDateTime orderTime;
}
