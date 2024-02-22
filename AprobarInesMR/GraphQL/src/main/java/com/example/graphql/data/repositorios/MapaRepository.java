package com.example.graphql.data.repositorios;

import com.example.graphql.data.modelo.MapaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapaRepository extends ListCrudRepository<MapaEntity, Integer> {
}
