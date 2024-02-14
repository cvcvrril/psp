package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.VideojuegoEntity;
import com.example.graphql.data.modelo.error.ErrorObject;
import com.example.graphql.data.repositorios.VideojuegoRepository;
import com.example.graphql.domain.modelo.Videojuego;
import com.example.graphql.domain.modelo.mapper.VideojuegoEntityMapper;
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
            res = Either.left(new ErrorObject("Hubo un problema con la lista", LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Videojuego> addVideojuego(String titulo, String descripcion) {
        Either<ErrorObject, Videojuego> res;
        VideojuegoEntity newVideojuegoEntity = new VideojuegoEntity(0, titulo, descripcion, new ArrayList<>());
        try {
            repository.save(newVideojuegoEntity);
            res = Either.right(mapper.toVideojuego(newVideojuegoEntity));
        }catch (Exception e){
            res = Either.left(new ErrorObject("Hubo un problema con la lista", LocalDateTime.now()));
        }
        return res;
    }
}
