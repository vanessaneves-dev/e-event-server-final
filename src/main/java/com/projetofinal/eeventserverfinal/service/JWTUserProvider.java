package com.projetofinal.eeventserverfinal.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class JWTUserProvider {

    @Value("${security.token.secret.user}")
    private String secretKey;

    public DecodedJWT validateToken(String token) {
        System.out.println("JWTUserProvider: Recebido token: " + token);
        token = token.replace("Bearer ", "");
        System.out.println("JWTUserProvider: Token após o replace: " + token);

        Algorithm algorithm = Algorithm.HMAC256(secretKey);


        try {
            DecodedJWT tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);
            System.out.println("JWTUserProvider: Token validado com sucesso, sujeito: " + tokenDecoded.getSubject());
            return tokenDecoded;

        } catch (JWTVerificationException e) {
            System.out.println("JWTUserProvider: Erro ao validar o token: " + e.getMessage());
            return null;
        }

//        try { var tokenDecoded =  JWT.require(algorithm)
//                .build()
//                .verify(token);
//            return tokenDecoded;
//
//        } catch (JWTVerificationException e) {
//            e.printStackTrace();
//            return null;
//        }
// retirada pois estava dando erro e não chamava o DecodeJWT


    }
}



