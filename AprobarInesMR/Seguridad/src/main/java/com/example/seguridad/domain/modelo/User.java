package com.example.seguridad.domain.modelo;

public record User(Long id,
                   String username,
                   String password,
                   Rol rol) {
}
