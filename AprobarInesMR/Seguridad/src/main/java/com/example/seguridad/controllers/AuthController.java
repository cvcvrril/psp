package com.example.seguridad.controllers;

import com.example.seguridad.servicios.AuthServicio;
import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Context;
import org.springframework.security.core.token.Token;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/auth")
public class AuthController {

    private final AuthServicio servicio;

    @PostMapping("/registro")
    public String registroAuth(){
        return null;
        //return servicio;
    }

    @PostMapping("/login")
    public String loginAuth(){
        //return servicio;
        return null;
    }

    @PostMapping("/refreshToken")
    public Token actualizarTokenAccess(@Context HttpServletResponse response, @Context HttpServletRequest request){
        String refreshToken = request.getHeader("refreshToken");

        try {

            //INFO: de alguna forma tengo que poder sacar las cosas del refreshToken
            String username = "";
            String roles = "";

            String newAccessToken =  generarTokenJWT(120, username, roles);

            response.setHeader(HttpHeaders.AUTHORIZATION, newAccessToken);
        } catch (Exception e) {
            throw new RuntimeException("Ha habido un problema al generar el nuevo Access Token");
        }

        //return servicio;
        return null;
    }

    private String generarTokenJWT(int expirationSeconds, String username, String rol) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
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
