package com.projetofinal.eeventserverfinal.service;


import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity execute(UserEntity userEntity){
        this.userRepository
                .findByEmailOrUsername(userEntity.getEmail(), userEntity.getUsername())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });


        return this.userRepository.save(userEntity);
    };
}
