package com.example.graphqlserverjavainesmr.domain.modelo;

import java.util.List;

public record Videojuego(
    int id,
    String titulo,
    String descripcion,
    List<Personaje> personajes
    //Mapa mapa
) {
}
