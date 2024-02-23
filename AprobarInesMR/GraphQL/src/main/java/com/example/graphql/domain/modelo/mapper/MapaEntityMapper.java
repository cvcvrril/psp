package com.example.graphql.domain.modelo.mapper;

import com.example.graphql.data.modelo.MapaEntity;
import com.example.graphql.domain.modelo.Mapa;
import com.example.graphql.utils.Constantes;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.COMPONENT_MODEL_SPRING)
public interface MapaEntityMapper {

    Mapa toMapa(MapaEntity mapaEntity);
}
