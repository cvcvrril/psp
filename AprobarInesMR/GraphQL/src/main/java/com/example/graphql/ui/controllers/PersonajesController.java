package com.example.graphql.ui.controllers;


import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.servicio.PersonajeServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonajesController {

    private final PersonajeServicio servicio;

    @QueryMapping
    public List<Personaje> getPersonajes() {
        return servicio.getPersonajes();
    }

}
