package com.example.graphql.ui.controllers;

import com.example.graphql.domain.modelo.Mapa;
import com.example.graphql.domain.servicio.MapaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MapasController {

    private final MapaServicio servicio;

    @QueryMapping
    public List<Mapa> getMapas(){
        return servicio.getMapas();
    }

}
