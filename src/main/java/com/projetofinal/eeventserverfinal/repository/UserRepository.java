package com.projetofinal.eeventserverfinal.repository;


import com.projetofinal.eeventserverfinal.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {


    Optional<UserEntity> findByEmail(String email);

}
