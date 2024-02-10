package com.example.graphqlserverjavainesmr.domain.servicio;

import com.example.graphqlserverjavainesmr.data.repositorios.VideojuegoRepository;
import com.example.graphqlserverjavainesmr.domain.modelo.Videojuego;
import com.example.graphqlserverjavainesmr.domain.modelo.mapper.VideojuegoEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VideojuegoServicio {

    private final VideojuegoRepository repository;
    private final VideojuegoEntityMapper mapper;

    public List<Videojuego> findVideojuegos() {
        return repository.findVideojuegos().stream()
                .map(mapper::toVideojuego)
                .toList();
    }
}
