package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.data.repositorios.PersonajeRepository;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.mapper.PersonajeEntityMapper;
import com.example.graphql.ui.exceptions.NotFoundException;
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

    public void deletePersonaje(int id){
        PersonajeEntity personajeEntitySel = repository.findById(id).get();
        if (personajeEntitySel != null){
            repository.delete(personajeEntitySel);
        }else {
            throw new NotFoundException("No se encontró el videojuego seleccionado");
        }
    }

    public Personaje getPersonaje(int id) {
        PersonajeEntity personajeEntity =  repository.findById(id).orElseThrow(() -> new NotFoundException("Personaje no encontrado"));
        return mapper.toPersonaje(personajeEntity);
    }
}
