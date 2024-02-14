package com.example.graphql.ui.controllers;


import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.servicio.PersonajeServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonajesController {

    private final PersonajeServicio servicio;

    @QueryMapping
    public List<Personaje> getPersonajes() {
        return servicio.getPersonajes();
    }

}
