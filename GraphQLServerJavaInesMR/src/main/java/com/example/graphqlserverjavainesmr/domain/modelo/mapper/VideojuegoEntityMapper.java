package com.example.graphqlserverjavainesmr.domain.modelo.mapper;

import com.example.graphqlserverjavainesmr.data.modelo.MapaEntity;
import com.example.graphqlserverjavainesmr.data.modelo.PersonajeEntity;
import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;
import com.example.graphqlserverjavainesmr.domain.modelo.Mapa;
import com.example.graphqlserverjavainesmr.domain.modelo.Personaje;
import com.example.graphqlserverjavainesmr.domain.modelo.Videojuego;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VideojuegoEntityMapper {
    Videojuego toVideojuego(VideojuegoEntity videojuegoEntity);
    Personaje toPersonaje(PersonajeEntity personajeEntity);
    Mapa toMapa(MapaEntity mapaEntity);
}
