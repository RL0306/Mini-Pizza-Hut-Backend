package com.example.springsecurity.repository;

import com.example.springsecurity.entity.CardEntity;
import com.example.springsecurity.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    Optional<CardEntity> findCardById(Long id);
    Optional<List<CardEntity>> findCardEntityByUserEntity(UserEntity userEntity);
    Optional<CardEntity> findCardEntityByCardNameAndAccountNumberAndSecurityCode(String cardName, String accountNumber, String securityCode);
}
