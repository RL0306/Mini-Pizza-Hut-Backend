package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDTO;
import com.example.springsecurity.entity.CardEntity;
import com.example.springsecurity.entity.OrderEntity;
import com.example.springsecurity.entity.UserEntity;
import com.example.springsecurity.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper mapper;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(s).orElseThrow();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole().name()));

        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }

    public List<UserDTO> getAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        return userEntities.stream().map(user -> mapper.convertValue(user, UserDTO.class)).collect(Collectors.toUnmodifiableList());
    }

    public UserDTO get(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();

        //set null or load card entities
        //get persistent bag instead of list of card entities

//        userEntity.setOrderEntities(userEntity.getOrderEntities());
//        userEntity.setCardEntities(userEntity.getCardEntities());

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        return userDTO;
    }
}
