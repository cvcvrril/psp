package com.example.seguridad.seguridad;

public interface Encriptacion {

    String encriptar(String texto,String secret);

    String desencriptar(String texto,String secret);

}
