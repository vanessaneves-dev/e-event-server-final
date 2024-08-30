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

    // Novo método para listar todos os usuários ---quando impelmentas ADMIN
    public List<ProfileUserResponseDTO> getAllUsers() {
        var users = this.userRepository.findAll();
        return users.stream().map(user ->
                ProfileUserResponseDTO.builder()
                        .email(user.getEmail())
                        .name(user.getName())
                        .username(user.getUsername())
                        .image(user.getImage())
                        .id(user.getId())
                        .build()
        ).toList();
    }


    public ProfileUserResponseDTO executeProfile (UUID idUser) {
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


    // Método para atualizar os dados do usuário
    public ProfileUserResponseDTO updateUser(UUID idUser, UserEntity updatedUser) {
        var user = this.userRepository.findById(idUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
        user.setUsername(updatedUser.getUsername());
        user.setImage(updatedUser.getImage());

        this.userRepository.save(user);

        return ProfileUserResponseDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .image(user.getImage())
                .id(user.getId())
                .build();
    }

    // Método para deletar um usuário
    public void deleteUser(UUID idUser) {
        var user = this.userRepository.findById(idUser)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        this.userRepository.delete(user);
    }


}
