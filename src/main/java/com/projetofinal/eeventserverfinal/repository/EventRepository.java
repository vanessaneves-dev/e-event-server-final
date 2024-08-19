package com.projetofinal.eeventserverfinal.repository;

import com.projetofinal.eeventserverfinal.models.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventEntity, UUID> {
}