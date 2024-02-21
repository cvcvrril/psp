package com.example.seguridad.domain.servicios;

import com.example.seguridad.config.Configuration;
import com.example.seguridad.data.modelo.UserEntity;
import com.example.seguridad.data.modelo.error.ErrorObjectSeguridad;
import com.example.seguridad.data.repositorios.UserRepository;
import io.jsonwebtoken.Jwts;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
public class JwtService {


    @Value("${KeyStorePassword}")
    private String keyStorePassword;

    private final UserRepository repository;
    private final Configuration configuration;

    public JwtService(UserRepository repository, Configuration configuration) {
        this.repository = repository;
        this.configuration = configuration;
    }

    private Key clavePrivadaKeyStore() {
        String passwordString = "Prueba";
        char[] password = passwordString.toCharArray();
        try (FileInputStream fis = new FileInputStream("keystore.pfx")){
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(fis, password);
            return keyStore.getKey("server", password);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Either<ErrorObjectSeguridad, String> generateToken(String username, int duration){
        Either<ErrorObjectSeguridad, String> res;
        try {
            UserEntity user = repository.findByUsername(username).get();

            if (user!= null){

                String token = Jwts.builder()
                        .setSubject(user.getUsername())
                        .setIssuer(user.getUsername())
                        .setExpiration(Date.from(LocalDateTime.now().plusSeconds(duration).atZone(ZoneId.systemDefault()).toInstant()))
                        .claim("user", user.getPassword())
                        .claim("rol", user.getRoles())
                        .signWith(clavePrivadaKeyStore())
                        .compact();
                res = Either.right(token);
            }else {
             res = Either.left(new ErrorObjectSeguridad("No se pudo encontrar al usuario", LocalDateTime.now()));
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return res;
    }

}
