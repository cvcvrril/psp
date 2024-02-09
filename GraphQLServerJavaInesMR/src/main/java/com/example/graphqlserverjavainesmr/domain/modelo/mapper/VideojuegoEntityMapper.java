package com.example.graphqlserverjavainesmr.domain.modelo.mapper;

import com.example.graphqlserverjavainesmr.data.modelo.PersonajeEntity;
import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;
import com.example.graphqlserverjavainesmr.domain.modelo.Personaje;
import com.example.graphqlserverjavainesmr.domain.modelo.Videojuego;
import com.example.graphqlserverjavainesmr.domain.modelo.graphql.VideojuegoInput;
import org.mapstruct.Mapper;


//Preguntar por qué me está saltando una excepción sobre los mappers

@Mapper(componentModel = "spring")
public interface VideojuegoEntityMapper {
    Videojuego toVideojuego(VideojuegoEntity videojuegoEntity);
    Personaje toPersonaje(PersonajeEntity personajeEntity);
}
