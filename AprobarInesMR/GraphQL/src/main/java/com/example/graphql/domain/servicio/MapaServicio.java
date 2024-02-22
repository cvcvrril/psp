package com.example.graphql.domain.servicio;

import com.example.graphql.data.repositorios.MapaRepository;
import com.example.graphql.domain.modelo.Mapa;
import com.example.graphql.domain.modelo.mapper.MapaEntityMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapaServicio {

    private final MapaRepository repository;
    private final MapaEntityMapper mapper;

    public MapaServicio(MapaRepository repository, MapaEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Mapa> getMapas(){
        return repository.findAll().stream()
                .map(mapper::toMapa)
                .toList();
    }
}
