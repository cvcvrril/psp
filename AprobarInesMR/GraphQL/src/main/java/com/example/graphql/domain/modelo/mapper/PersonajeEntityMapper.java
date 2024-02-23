package com.example.graphql.domain.modelo.mapper;


import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.Videojuego;
import com.example.graphql.domain.modelo.graphql.PersonajeInput;
import com.example.graphql.utils.Constantes;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.COMPONENT_MODEL_SPRING)
public interface PersonajeEntityMapper {
    Personaje toPersonaje(PersonajeEntity personajeEntity);
    Personaje toPersonaje(PersonajeInput personajeInput);
    PersonajeEntity toPersonajeEntity(Personaje personaje);
}
