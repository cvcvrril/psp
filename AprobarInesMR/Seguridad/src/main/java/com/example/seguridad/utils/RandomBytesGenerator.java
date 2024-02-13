package com.example.seguridad.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class RandomBytesGenerator {

    //INFO: método para generar la KSA

    public String randomBytes() {
        SecureRandom sr = new SecureRandom();
        byte[] bits = new byte[32];
        sr.nextBytes(bits);
        return Base64.getUrlEncoder().encodeToString(bits);
    }
}
