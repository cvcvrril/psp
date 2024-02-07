package com.example.graphqlserverjavainesmr.ui.controllers;

import com.example.graphqlserverjavainesmr.data.repositorios.VideojuegoRepository;
import com.example.graphqlserverjavainesmr.domain.modelo.Videojuego;
import com.example.graphqlserverjavainesmr.domain.servicio.VideojuegoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoServicio servicio;

    public List<Videojuego> getVideojuegos() {
        return servicio.getVideojuegos();
    }


}
