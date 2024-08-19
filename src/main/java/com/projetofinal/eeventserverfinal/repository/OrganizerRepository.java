package com.projetofinal.eeventserverfinal.repository;

import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrganizerRepository extends JpaRepository<OrganizerEntity, UUID>{
    Optional<OrganizerEntity> findByEmailOrUsername(String email, String username);
    Optional<OrganizerEntity> findByEmail(String email);
}
