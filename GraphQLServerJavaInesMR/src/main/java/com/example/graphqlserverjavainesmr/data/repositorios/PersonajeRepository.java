package com.example.graphqlserverjavainesmr.data.repositorios;


import com.example.graphqlserverjavainesmr.data.modelo.PersonajeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonajeRepository extends ListCrudRepository<PersonajeEntity, Integer> {

    @Query(""" 
    select p from PersonajeEntity p 
    JOIN FETCH p.nombre nombre
    JOIN FETCH p.descripcion descripcion
    JOIN FETCH p.videojuego videojuego
    where p.id = :id
    """)
    Optional<PersonajeEntity> findEnteroById(Long id);

}
