package com.example.springsecurity.dto;


import lombok.Data;

@Data
public class CardDTO {
    private Long id;
    private String cardName;
    private String accountNumber;
    private String sortCode;
    private String securityCode;
    private double balance;
}
