package com.example.graphql.ui.controllers;


import com.example.graphql.domain.modelo.User;
import com.example.graphql.domain.servicio.UserServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServicio servicio;

    @PostMapping("/inesregistrp")
    public User registro(@RequestBody User newUser){
        return servicio.add(newUser);
    }


    private String generarTokenJWT(int expirationSeconds, String username, String rol) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            //throw new WrongObjectException(ConstantsJakarta.HUBO_UN_PROBLEMA);
            throw new RuntimeException("Hubo un problema");
        }
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("self")
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(expirationSeconds).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("rol", rol)
                .signWith(keyConfig).compact();
    }


}
