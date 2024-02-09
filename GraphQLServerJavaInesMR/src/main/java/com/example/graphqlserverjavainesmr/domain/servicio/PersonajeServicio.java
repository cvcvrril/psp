package com.example.graphqlserverjavainesmr.domain.servicio;

import com.example.graphqlserverjavainesmr.data.repositorios.PersonajeRepository;
import com.example.graphqlserverjavainesmr.domain.modelo.Personaje;
import com.example.graphqlserverjavainesmr.domain.modelo.mapper.PersonajeEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonajeServicio {

    private final PersonajeRepository repository;
    private final PersonajeEntityMapper mapper;

    public PersonajeServicio(PersonajeRepository repository, PersonajeEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Personaje> getPersonajes() {
        return repository.findPersonajes().stream()
                .map(mapper::toPersonaje)
                .toList();
    }
}
