package com.example.graphql.domain.modelo;


import com.example.graphql.data.modelo.VideojuegoEntity;

public record Personaje(
        int id,
        String nombre,
        String descripcion,
        VideojuegoEntity videojuego
) {


}
