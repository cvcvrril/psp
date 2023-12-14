package dao;

import dao.modelo.Videojuego;
import jakarta.excepciones.ApiError;
import io.vavr.control.Either;

import java.util.List;

public interface DaoVideojuego {

    Either<ApiError, List<Videojuego>> getAll();

}
