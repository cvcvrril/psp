package com.example.graphql.domain.modelo.mapper;

import com.example.graphql.data.modelo.ConsolaEntity;
import com.example.graphql.domain.modelo.Consola;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ConsolaEntityMapper {

    Consola toConsola(ConsolaEntity consolaEntity);

}
