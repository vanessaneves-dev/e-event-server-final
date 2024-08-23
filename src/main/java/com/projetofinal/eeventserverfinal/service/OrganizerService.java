package com.projetofinal.eeventserverfinal.service;

import com.projetofinal.eeventserverfinal.exceptions.UserFoundException;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
