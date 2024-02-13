package com.example.graphql.domain.modelo;

import java.util.List;

public record User(Long id,
                   String username,
                   String password,
                   List<Rol> roles) {
}
