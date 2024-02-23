package com.example.graphql.data.repositorios;


import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.utils.Constantes;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideojuegoRepository extends ListCrudRepository<VideojuegoEntity, Integer> {

    @Query(Constantes.QUERY_FIND_VIDEOJUEGOS)
    List<VideojuegoEntity> findVideojuegos();

    @Query(""" 
    select v from VideojuegoEntity v
    join fetch v.personajes
    join fetch v.mapa
    where v.id = :id
    """)
    Optional<VideojuegoEntity> findAllById(int id);

}
