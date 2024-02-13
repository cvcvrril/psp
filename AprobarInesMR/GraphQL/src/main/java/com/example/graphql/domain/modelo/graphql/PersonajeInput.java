package com.example.graphql.domain.modelo.graphql;

public record PersonajeInput(
        int id,
        String nombre,
        String descripcion,
        VideojuegoInput videojuego
) {
}
