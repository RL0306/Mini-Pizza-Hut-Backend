package com.example.springsecurity.controller;

import com.example.springsecurity.dto.PizzaDTO;
import com.example.springsecurity.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pizza")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;


    //understand roles/authorities
    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<PizzaDTO> add(@RequestBody PizzaDTO pizzaDTO){
        PizzaDTO pizza = pizzaService.add(pizzaDTO);
        return ResponseEntity.ok(pizza);
    }

    @GetMapping
    public ResponseEntity<List<PizzaDTO>> all(){
        List<PizzaDTO> pizzas = pizzaService.getAll();
        return ResponseEntity.ok(pizzas);
    }
}
