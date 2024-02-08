package com.example.graphqlserverjavainesmr.domain.modelo.graphql;

public record PersonajeInput(
        int id,
        String nombre,
        String descripcion,
        VideojuegoInput videojuego
) {
}
