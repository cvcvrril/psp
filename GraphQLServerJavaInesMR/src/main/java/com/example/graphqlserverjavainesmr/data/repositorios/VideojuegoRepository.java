package com.example.graphqlserverjavainesmr.data.repositorios;

import com.example.graphqlserverjavainesmr.data.modelo.VideojuegoEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideojuegoRepository extends ListCrudRepository<VideojuegoEntity, Integer> {
}
