package com.projetofinal.eeventserverfinal.service;


import com.projetofinal.eeventserverfinal.dto.ProfileUserResponseDTO;
import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserEntity execute(UserEntity userEntity){
        this.userRepository
                .findByEmail(userEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var pasword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(pasword);
        return this.userRepository.save(userEntity);
    };

    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


    public ProfileUserResponseDTO execute (UUID idUser) {
       var user =  this.userRepository.findById(idUser)
                .orElseThrow(() ->
                    new UsernameNotFoundException("User not found")
                );
       var userDTO = ProfileUserResponseDTO.builder()
               .email(user.getEmail())
               .name(user.getName())
               .password(user.getPassword())
               .username(user.getUsername())
               .image(user.getImage())
               .id(user.getId())
               .build();
        return userDTO;
             }



    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }


}
