package com.example.springsecurity.service;

import com.example.springsecurity.dto.CardDTO;
import com.example.springsecurity.dto.OrderDTO;
import com.example.springsecurity.dto.PizzaDTO;
import com.example.springsecurity.entity.CardEntity;
import com.example.springsecurity.entity.OrderEntity;
import com.example.springsecurity.entity.PizzaEntity;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.repository.OrderRepository;
import com.example.springsecurity.repository.PizzaRepository;
import com.example.springsecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final PizzaRepository pizzaRepository;
    private final CardRepository cardRepository;
    private final CardService cardService;
    private final ModelMapper mapper = new ModelMapper();


    public OrderDTO create(Long id, CardDTO cardDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(user.getUsername()).orElseThrow();
        CardEntity card = cardService.get(cardDTO);
        PizzaEntity pizzaEntity = pizzaRepository.findById(id).orElseThrow();

        if(card.getBalance() > pizzaEntity.getPrice()){


            OrderEntity orderEntity = new OrderEntity(userEntity, pizzaEntity, pizzaEntity.getPrice(), card);
            orderRepository.save(orderEntity);
            card.setBalance(card.getBalance() - pizzaEntity.getPrice());
            cardRepository.save(card);
            return mapper.map(orderEntity, OrderDTO.class);
        } else{
            throw new IllegalStateException("Insufficient funds!");
        }


    }


}
