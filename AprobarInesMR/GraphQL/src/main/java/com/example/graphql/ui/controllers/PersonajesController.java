package com.example.graphql.ui.controllers;


import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.servicio.PersonajeServicio;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PersonajesController {

    private final PersonajeServicio servicio;

    @QueryMapping
    @RolesAllowed({"USER", "ADMIN"})
    public List<Personaje> getPersonajes() {
        return servicio.getPersonajes();
    }

}
