package com.projetofinal.eeventserverfinal.service;


import com.auth0.jwt.algorithms.Algorithm;
import com.projetofinal.eeventserverfinal.dto.AuthOrganizerDTO;
import com.projetofinal.eeventserverfinal.dto.AuthOrganizerResponseDTO;
import com.projetofinal.eeventserverfinal.models.OrganizerEntity;
import com.projetofinal.eeventserverfinal.repository.OrganizerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;

import javax.naming.AuthenticationException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;


@Service
public class AuthOrganizerService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private OrganizerRepository organizerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthOrganizerResponseDTO execute(AuthOrganizerDTO authOrganizerDTO) throws AuthenticationException {
        var organizer = this.organizerRepository.findByEmail(authOrganizerDTO.getEmail()).orElseThrow(
                () -> {
                    throw new UsernameNotFoundException("Usuário ou senha inválidos");
                });
        //Veirficação das senhas
          var passwordMatches =  this.passwordEncoder.matches(authOrganizerDTO.getPassword(), organizer.getPassword());
            //senhas diferentes
            if (!passwordMatches) {
                throw new AuthenticationException();
            }
            var expiresIn = Instant.now().plus(Duration.ofHours(5));

            //senhas iguais -> gerar token
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
           var token =  JWT.create().withIssuer("event")
                   .withExpiresAt(Instant.now().plus(Duration.ofHours(5)))
                   .withSubject(organizer.getId().toString())
                   .withClaim("roles", Arrays.asList("ORGANIZER"))
                   .sign(algorithm);

       var authOrganizerResponseDTO  = AuthOrganizerResponseDTO.builder()
                .access_organizer_token(token)
                .expires_in(expiresIn.toEpochMilli())
               .build();
           return authOrganizerResponseDTO;
    }

}
