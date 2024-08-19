package com.projetofinal.eeventserverfinal.service;

import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    public OrganizerEntity execute(OrganizerEntity organizerEntity) {

        this.organizerRepository.findByEmailOrUsername(organizerEntity.getEmail(), organizerEntity.getUsername())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        return this.organizerRepository.save(organizerEntity);

    }
}
