package com.projetofinal.eeventserverfinal.service;

import com.projetofinal.eeventserverfinal.dto.ProfileOrganizerResponseDTO;
import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrganizerService {

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public OrganizerEntity execute(OrganizerEntity organizerEntity) {

        this.organizerRepository.findByEmail(organizerEntity.getEmail())
                .ifPresent((user) -> {
                    throw new UserFoundException();
                });

        var pasword = passwordEncoder.encode(organizerEntity.getPassword());
        organizerEntity.setPassword(pasword);

        return this.organizerRepository.save(organizerEntity);

    }

    // Novo método para listar todos os organizadores
    public List<OrganizerEntity> getAllOrganizers() {
        return this.organizerRepository.findAll();
    }

    // Novo método para obter um organizador por ID
    public OrganizerEntity getOrganizerById(UUID id) {
        return this.organizerRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Organizer not found"));
    }

    // Novo método para atualizar um organizador
    public OrganizerEntity updateOrganizer(UUID id, OrganizerEntity updatedOrganizer) {
        var organizer = this.organizerRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Organizer not found"));

        organizer.setName(updatedOrganizer.getName());
        organizer.setEmail(updatedOrganizer.getEmail());
        organizer.setUsername(updatedOrganizer.getUsername());
        organizer.setImage(updatedOrganizer.getImage());

        return this.organizerRepository.save(organizer);
    }

    // Novo método para deletar um organizador
    public void deleteOrganizer(UUID id) {
        var organizer = this.organizerRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Organizer not found"));
        this.organizerRepository.delete(organizer);
    }

    public ProfileOrganizerResponseDTO executeProfile (UUID idOrganizer) {
        var organizer =  this.organizerRepository.findById(idOrganizer)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Organizer not found")
                );
        var organizerDTO = ProfileOrganizerResponseDTO.builder()
                .email(organizer.getEmail())
                .name(organizer.getName())
                .password(organizer.getPassword())
                .username(organizer.getUsername())
                .image(organizer.getImage())
                .id(organizer.getId())
                .build();
        return organizerDTO;
    }




}
