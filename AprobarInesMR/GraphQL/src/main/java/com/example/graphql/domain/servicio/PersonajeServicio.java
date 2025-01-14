package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.data.repositorios.PersonajeRepository;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.graphql.PersonajeInput;
import com.example.graphql.domain.modelo.mapper.PersonajeEntityMapper;
import com.example.graphql.ui.exceptions.NotFoundException;
import com.example.graphql.utils.Constantes;
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

    public Personaje getPersonaje(int id) {
        PersonajeEntity personajeEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(Constantes.ERROR_PERSONAJE_NO_ENCONTRADO));
        return mapper.toPersonaje(personajeEntity);
    }

    public void deletePersonaje(int id) {
        try {
            PersonajeEntity personajeEntitySel = repository.findById(id).orElseThrow(() -> new NotFoundException(Constantes.ERROR_PERSONAJE_NO_ENCONTRADO));
            repository.delete(personajeEntitySel);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }

    }

    public Personaje addPersonaje(String nombre) {
        PersonajeEntity newPersonajeEntity = new PersonajeEntity(0, nombre, com.example.seguridad.utils.Constantes.DESCRIPCION_POR_DEFECTO_PERSONAJE, null);
        repository.save(newPersonajeEntity);
        return mapper.toPersonaje(newPersonajeEntity);
    }

    public Personaje updatePersonaje(PersonajeInput personajeInput){
        Personaje updatedPersonajeObjeto = mapper.toPersonaje(personajeInput);
        PersonajeEntity personajeEntity = mapper.toPersonajeEntity(updatedPersonajeObjeto);
        repository.save(personajeEntity);
        return updatedPersonajeObjeto;
    }

    private int genIds(){
        return getPersonajes().size() + 1;
    }

}
