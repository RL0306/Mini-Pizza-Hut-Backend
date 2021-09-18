package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardName;
    private String accountNumber;
    private String sortCode;
    private String securityCode;
    private double balance;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity userEntity;

    public CardEntity(String cardName, String accountNumber, String sortCode, String securityCode, UserEntity userEntity) {
        this.cardName = cardName;
        this.accountNumber = accountNumber;
        this.sortCode = sortCode;
        this.securityCode = securityCode;
        this.balance = 0;
        this.userEntity = userEntity;

    }
}
