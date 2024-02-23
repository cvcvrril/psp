package com.example.graphql.data.repositorios;



import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.utils.Constantes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends ListCrudRepository<PersonajeEntity, Integer> {

    @Query(Constantes.QUERY_FIND_PERSONAJES)
    List<PersonajeEntity> findPersonajes();

}
