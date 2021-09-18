package com.example.springsecurity.service;

import com.example.springsecurity.dto.CardBalanceDTO;
import com.example.springsecurity.dto.CardDTO;
import com.example.springsecurity.entity.CardEntity;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.CardRepository;
import com.example.springsecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ModelMapper mapper = new ModelMapper();
    private final UserRepository userRepository;

    public CardDTO add(CardDTO cardRequest) {
        //get user

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity userEntity = userRepository.findByUsername(authUser.getUsername()).orElseThrow(() -> new IllegalStateException("User does not exist"));


        CardEntity cardEntityNew = new CardEntity(cardRequest.getCardName(),cardRequest.getAccountNumber(), cardRequest.getSortCode(), cardRequest.getSecurityCode(), userEntity);
        cardRepository.save(cardEntityNew);

        return mapper.map(cardEntityNew, CardDTO.class);

    }

    public CardDTO remove(Long id){
        CardEntity cardEntity = cardRepository.findCardById(id).orElseThrow(()-> new IllegalStateException("Card cannot be found"));

        CardDTO cardDTO =  mapper.map(cardEntity, CardDTO.class);

        cardRepository.delete(cardEntity);

        return cardDTO;


    }

    public CardEntity get(CardDTO cardDTO){

        CardEntity cardEntity = cardRepository.findCardEntityByCardNameAndAccountNumberAndSecurityCode(
                cardDTO.getCardName(), cardDTO.getAccountNumber(), cardDTO.getSecurityCode()
        ).orElseThrow();


        return cardEntity;
    }


    //HIBERNATE WEIRD SHIT HAPPENS HERE
    public CardDTO update(Long id, CardBalanceDTO cardBalanceDTO) {
        CardEntity cardEntity = cardRepository.findCardById(id).orElseThrow(()-> new IllegalStateException("Card cannot be found"));
        cardEntity.setBalance(cardEntity.getBalance() + cardBalanceDTO.getBalance());
        //fixes
        cardRepository.save(cardEntity);

        return  mapper.map(cardEntity, CardDTO.class);
    }

    public List<CardDTO> get() {

        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Object authUser1 = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(authUser1);

        UserEntity userEntity = userRepository.findByUsername(authUser.getUsername()).orElseThrow();
        log.info(userEntity.getUsername());
        List<CardEntity> cardEntityList = cardRepository.findCardEntityByUserEntity(userEntity).orElseThrow();

        List<CardDTO> cardDtos =  cardEntityList.stream().map(card ->  mapper.map(card, CardDTO.class)).collect(Collectors.toList());
        log.info(String.valueOf(cardDtos));

        return cardDtos;

    }

}
