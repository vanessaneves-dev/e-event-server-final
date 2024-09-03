package com.projetofinal.eeventserverfinal.repository;


import com.projetofinal.eeventserverfinal.models.EventEntity;
import com.projetofinal.eeventserverfinal.models.UserEntity;
import com.projetofinal.eeventserverfinal.models.UserEventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserEventRepository extends JpaRepository<UserEventEntity, UUID> {
    Optional<UserEventEntity> findByUserAndEvent(UserEntity userEntity, EventEntity eventEntity);
    List<UserEventEntity> findByUserIdAndIsConfirmedTrue(UUID userId);
    List<UserEventEntity> findByUserIdAndIsFavoritedTrue(UUID userId);
}
