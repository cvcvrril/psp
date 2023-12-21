package dao;

import dao.modelo.Videojuego;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DaoVideojuego {

    Either<ApiError, List<Videojuego>> getAll();

}
