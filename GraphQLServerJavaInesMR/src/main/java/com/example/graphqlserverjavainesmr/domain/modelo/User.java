package com.example.graphqlserverjavainesmr.domain.modelo;

import java.util.List;

public record User(int id,
                   String username,
                   String password,
                   List<Rol> roles) {
}
