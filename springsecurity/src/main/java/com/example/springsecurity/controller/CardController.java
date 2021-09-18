package com.example.springsecurity.controller;

import com.example.springsecurity.dto.CardBalanceDTO;
import com.example.springsecurity.dto.CardDTO;
import com.example.springsecurity.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/card")
@CrossOrigin("*")
public class CardController {
    private final CardService cardService;

    @PostMapping()
    public ResponseEntity<CardDTO> add(@RequestBody CardDTO cardRequest){
        CardDTO cardDTO = cardService.add(cardRequest);
        return ResponseEntity.ok(cardDTO);
    }

    @GetMapping
    public ResponseEntity<List<CardDTO>> get(){

        List<CardDTO> cardDTOList = cardService.get();
        return ResponseEntity.ok(cardDTOList);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<CardDTO> remove(@PathVariable Long id){
        CardDTO cardDTO = cardService.remove(id);
        return ResponseEntity.ok(cardDTO);
    }

//    @GetMapping("/get")
//    public ResponseEntity<?> getCard(@RequestBody CardDTO cardDTORequest){
//        CardDTO cardDTO = cardService.get(cardDTORequest);
//        return ResponseEntity.ok(cardDTO);
//    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CardDTO> update(@RequestBody CardBalanceDTO cardBalanceDTO, @PathVariable("id") Long id){
        CardDTO cardDTO = cardService.update(id ,cardBalanceDTO);
        return ResponseEntity.ok(cardDTO);
    }
}
