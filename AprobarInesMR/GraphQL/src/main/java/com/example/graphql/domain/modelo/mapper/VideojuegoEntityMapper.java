package com.example.graphql.domain.modelo.mapper;


import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.Videojuego;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VideojuegoEntityMapper {
    Videojuego toVideojuego(VideojuegoEntity videojuegoEntity);

}
