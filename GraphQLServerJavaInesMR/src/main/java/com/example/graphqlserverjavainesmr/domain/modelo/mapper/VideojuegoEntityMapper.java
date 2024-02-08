package com.example.graphqlserverjavainesmr.domain.modelo.mapper;

import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;
import com.example.graphqlserverjavainesmr.domain.modelo.Videojuego;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VideojuegoEntityMapper {
    Videojuego toVideojuego(VideojuegoEntity videojuegoEntity);
}
