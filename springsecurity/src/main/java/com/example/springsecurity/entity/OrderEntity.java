package com.example.springsecurity.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne()
    private UserEntity userEntity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PizzaEntity pizzaEntity;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CardEntity cardEntity;

    private double price;

    @JsonFormat
    private LocalDateTime orderTime;

    public OrderEntity(UserEntity userEntity, PizzaEntity pizzaEntity, double price, CardEntity cardEntity){
        this.userEntity = userEntity;
        this.pizzaEntity = pizzaEntity;
        this.price = price;
        this.orderTime = LocalDateTime.now();
        this.cardEntity = cardEntity;
    }


}
