package com.example.graphqlserverjavainesmr.domain.modelo;

import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;

public record Personaje(
        int id,
        String nombre,
        String descripcion,
        VideojuegoEntity videojuego
) {


}
