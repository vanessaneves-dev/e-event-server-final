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
        token = token.replace("Bearer ", "");

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

//        try { var tokenDecoded =  JWT.require(algorithm)
//                .build()
//                .verify(token);
//            return tokenDecoded;
//
//        } catch (JWTVerificationException e) {
//            e.printStackTrace();
//            return null;
//        }
// retirada pois estava dando erro e não chamava o DecodeJWT, aula filtro de segurança candidato


        try {
            DecodedJWT tokenDecoded;
            tokenDecoded = JWT.require(algorithm)
                    .build()
                    .verify(token);
            return tokenDecoded;

        } catch (JWTVerificationException e) {
            e.printStackTrace();
            return null;
        }


    }



}
