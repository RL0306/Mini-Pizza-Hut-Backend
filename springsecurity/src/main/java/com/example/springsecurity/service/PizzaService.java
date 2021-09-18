package com.example.springsecurity.service;

import com.example.springsecurity.dto.PizzaDTO;
import com.example.springsecurity.entity.PizzaEntity;
import com.example.springsecurity.repository.PizzaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PizzaService {
    private final PizzaRepository pizzaRepository;
    private final ObjectMapper mapper;

    public PizzaDTO add(PizzaDTO pizzaDTO) {
        PizzaEntity pizzaEntity = new PizzaEntity(pizzaDTO.getName(), pizzaDTO.getDescription(), pizzaDTO.getPrice(), pizzaDTO.getPath());
        pizzaRepository.save(pizzaEntity);

        return mapper.convertValue(pizzaEntity, PizzaDTO.class);
    }

    public List<PizzaDTO> getAll() {
        List<PizzaEntity> pizzaEntities = pizzaRepository.findAll();

        List<PizzaDTO> pizzasDTO = pizzaEntities.stream().map(pizzaEntity -> mapper.convertValue(pizzaEntity, PizzaDTO.class)).collect(Collectors.toList());

        return pizzasDTO;
    }
}
