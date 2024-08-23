package com.projetofinal.eeventserverfinal.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.projetofinal.eeventserverfinal.dto.AuthUserEntityRequestDTO;
import com.projetofinal.eeventserverfinal.dto.AuthUserEntityResponseDTO;
import com.projetofinal.eeventserverfinal.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;

@Service
public class AuthUserEntityService {

    @Value("${security.token.secret.user}")
    private String secretKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthUserEntityResponseDTO execute (AuthUserEntityRequestDTO authUserEntityRequestDTO) throws AuthenticationException {
        var useEntity = this.userRepository.findByEmail(authUserEntityRequestDTO.email())
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("Email ou senha inválidos");
                });

        var passawordMatches = this.passwordEncoder.matches(authUserEntityRequestDTO.password(), useEntity.getPassword());
        if (!passawordMatches) {throw new BadCredentialsException("Email ou senha inválidos");
        }
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
       var token =  JWT.create()
               .withIssuer("event")
               .withSubject(useEntity.getId().toString())
               .withClaim("roles", Arrays.asList("USER"))
               .withExpiresAt(Instant.now().plus(Duration.ofHours(5)))
               .sign(algorithm);

        var  authUserEntityResponseDTO  = AuthUserEntityResponseDTO.builder()
                .access_user_token(token)
                .build();

        return authUserEntityResponseDTO;
    }
}
