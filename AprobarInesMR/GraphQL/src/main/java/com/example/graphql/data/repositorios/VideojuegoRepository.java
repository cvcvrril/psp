package com.example.graphql.data.repositorios;


import com.example.graphql.data.modelo.VideojuegoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideojuegoRepository extends ListCrudRepository<VideojuegoEntity, Integer> {

    @Query(""" 
    select v from VideojuegoEntity v 
    JOIN FETCH v.personajes
    """)
    List<VideojuegoEntity> findVideojuegos();

}
