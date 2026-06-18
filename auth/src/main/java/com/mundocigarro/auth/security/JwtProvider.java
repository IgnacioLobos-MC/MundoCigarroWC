package com.mundocigarro.auth.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private final String SECRET_KEY =
            "4a6f73654d617269615365637265744b6579323032364e6577536563757265546f6b656e47656e657261746f724d6963726f7365727669636573";

    public String generateToken(String username, String rol){

        return JWT.create()

                .withSubject(username)

                .withClaim("rol", rol)

                .withIssuedAt(new Date())

                .withExpiresAt(
                        new Date(
                                System.currentTimeMillis()
                                        + 86400000))

                .sign(
                        Algorithm.HMAC256(
                                SECRET_KEY));
    }
}