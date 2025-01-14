package dao.interfaces;

import dao.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DaoPersonaje {

    Either<ApiError, List<Personaje>> getAll();
    Either<ApiError, Integer> delete(int id);
    Either<ApiError, List<Personaje>> getIdVideojuego(int idVideojuego);
}
