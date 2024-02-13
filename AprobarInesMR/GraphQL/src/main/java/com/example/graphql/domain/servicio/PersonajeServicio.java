package com.example.graphql.domain.servicio;


import com.example.graphql.data.repositorios.PersonajeRepository;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.mapper.PersonajeEntityMapper;
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
