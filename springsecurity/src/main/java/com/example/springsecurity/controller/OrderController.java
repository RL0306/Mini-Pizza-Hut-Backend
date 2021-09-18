package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CardDTO;
import com.example.springsecurity.dto.OrderDTO;
import com.example.springsecurity.dto.PizzaDTO;
import com.example.springsecurity.entity.OrderEntity;
import com.example.springsecurity.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;


    @PostMapping(value = "{id}")
    private ResponseEntity<OrderDTO> create(@PathVariable Long id, @RequestBody CardDTO cardDTO){
        OrderDTO orderDTO = orderService.create(id, cardDTO);
        return ResponseEntity.ok(orderDTO);
    }
}
