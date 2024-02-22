package com.example.graphql.domain.modelo.mapper;

import com.example.graphql.data.modelo.MapaEntity;
import com.example.graphql.domain.modelo.Mapa;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapaEntityMapper {

    Mapa toMapa(MapaEntity mapaEntity);
}
