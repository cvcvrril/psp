package com.example.graphql.ui.controllers;


import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.graphql.PersonajeInput;
import com.example.graphql.domain.servicio.PersonajeServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonajeController {

    private final PersonajeServicio servicio;

    @QueryMapping
    //@RolesAllowed({"USER", "ADMIN"})
    public List<Personaje> getPersonajes() {
        return servicio.getPersonajes();
    }

    @QueryMapping
    public Personaje getPersonaje(@Argument int id){
        return servicio.getPersonaje(id);
    }

    @MutationMapping
    public Personaje addPersonaje(@Argument String nombre){
        return servicio.addPersonaje(nombre);
    }
    @MutationMapping
    public Personaje updatePersonaje(@Argument PersonajeInput personajeInput){
        return servicio.updatePersonaje(personajeInput);
    }

    @MutationMapping
    //@RolesAllowed({"USER", "ADMIN"})
    public void deletePersonaje(@Argument int id){
        servicio.deletePersonaje(id);
    }




}
