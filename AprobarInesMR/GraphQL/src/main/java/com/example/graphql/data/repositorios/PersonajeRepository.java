package com.example.graphql.data.repositorios;



import com.example.graphql.data.modelo.PersonajeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends ListCrudRepository<PersonajeEntity, Integer> {

    @Query(""" 
    select p from PersonajeEntity p 
    JOIN FETCH p.videojuego
    """)
    List<PersonajeEntity> findPersonajes();

}
