package com.example.seguridad.domain.servicios;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServicio {

    private final PasswordEncoder passwordHash;

    public AuthServicio(PasswordEncoder passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String hashPassword(String password){
        passwordHash.encode(password);
        return null;
    }

}
