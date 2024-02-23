package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.PersonajeEntity;
import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.data.modelo.error.ErrorObject;
import com.example.graphql.data.repositorios.PersonajeRepository;
import com.example.graphql.data.repositorios.VideojuegoRepository;
import com.example.graphql.domain.modelo.Personaje;
import com.example.graphql.domain.modelo.Videojuego;
import com.example.graphql.domain.modelo.mapper.VideojuegoEntityMapper;
import com.example.graphql.ui.exceptions.NotFoundException;
import com.example.graphql.utils.Constantes;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VideojuegoServicio {

    private final VideojuegoRepository repository;
    private final PersonajeRepository repositorySec;
    private final VideojuegoEntityMapper mapper;

    public Either<ErrorObject, List<Videojuego>> findVideojuegos() {
        Either<ErrorObject, List<Videojuego>> res;
        List<Videojuego> videojuegos;
        try {
            videojuegos = repository.findVideojuegos().stream()
                    .map(mapper::toVideojuego)
                    .toList();
            res = Either.right(videojuegos);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return res;
    }

    public Either<ErrorObject, Videojuego> addVideojuego(String titulo) {
        Either<ErrorObject, Videojuego> res;
        VideojuegoEntity newVideojuegoEntity = new VideojuegoEntity(0, titulo, Constantes.DESCRIPCION_POR_DEFECTO, new ArrayList<>(), null);
        try {
            repository.save(newVideojuegoEntity);
            res = Either.right(mapper.toVideojuego(newVideojuegoEntity));
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
        return res;
    }

    public void deleteVideojuego(int id){
        try {
            VideojuegoEntity videojuegoEntitySel = repository.findAllById(id).orElseThrow(() -> new NotFoundException(Constantes.ERROR_VIDEOJUEGO_NO_ENCONTRADO));
            repository.delete(videojuegoEntitySel);
        }catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }
    }

    public Videojuego getVideojuego(int id) {
        VideojuegoEntity videojuegoEntity = repository.findAllById(id).orElseThrow(() -> new NotFoundException(Constantes.ERROR_VIDEOJUEGO_NO_ENCONTRADO));
        return mapper.toVideojuego(videojuegoEntity);
    }
}
