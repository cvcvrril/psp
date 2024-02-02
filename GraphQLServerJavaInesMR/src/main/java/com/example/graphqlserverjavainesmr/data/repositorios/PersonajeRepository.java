package com.example.graphqlserverjavainesmr.data.repositorios;


import com.example.graphqlserverjavainesmr.data.modelo.PersonajeEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonajeRepository extends ListCrudRepository<PersonajeEntity, Integer> {
}
