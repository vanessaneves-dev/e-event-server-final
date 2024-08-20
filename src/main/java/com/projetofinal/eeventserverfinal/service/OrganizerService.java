package com.projetofinal.eeventserverfinal.service;

import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<OrganizerEntity> getOrganizerById(UUID id) {
        return this.organizerRepository.findById(id);
    }

    public List<OrganizerEntity> getAllOrganizers() {
        return this.organizerRepository.findAll();
    }

    public void deleteOrganizer(UUID id) {
        if (this.organizerRepository.existsById(id)) {
            this.organizerRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Organizer not found");
        }
    }
}
