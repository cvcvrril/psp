package com.example.graphqlserverjavainesmr.ui.controllers;

import com.example.graphqlserverjavainesmr.domain.modelo.Personaje;
import com.example.graphqlserverjavainesmr.domain.servicio.PersonajeServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PersonajesController {

    private final PersonajeServicio servicio;

    public List<Personaje> getPersonajes() {
        return servicio.getPersonajes();
    }

}
