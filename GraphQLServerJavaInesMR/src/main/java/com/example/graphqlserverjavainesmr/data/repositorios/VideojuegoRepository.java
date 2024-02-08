package com.example.graphqlserverjavainesmr.data.repositorios;

import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideojuegoRepository extends ListCrudRepository<VideojuegoEntity, Integer> {

    @Query(""" 
    select v from VideojuegoEntity v 
    JOIN FETCH v.titulo titulo
    JOIN FETCH v.descripcion descripcion
    JOIN FETCH v.personajes personajes
    where v.id = :id
    """)
    Optional<VideojuegoEntity> findEnteroById(Long id);

}
