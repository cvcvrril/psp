package com.example.graphql.ui.controllers;

import com.example.graphql.domain.modelo.Videojuego;
import com.example.graphql.domain.servicio.VideojuegoServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VideojuegoController {

    private final VideojuegoServicio servicio;

    @QueryMapping
    public List<Videojuego> getVideojuegos() {
        return servicio.findVideojuegos().get();
    }

    @MutationMapping
    public Videojuego addVideojuego(@RequestBody String titulo, String descripcion){
        return servicio.addVideojuego(titulo, descripcion).get();
    }

}
