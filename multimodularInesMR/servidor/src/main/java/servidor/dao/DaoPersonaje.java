package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;

import java.util.List;

public interface DaoPersonaje {
    Either<ApiError, List<Personaje>> getAll();
    Either<ApiError, Personaje> get(int i);
    Either<ApiError, Integer> add();
    Either<ApiError, Integer> update();
    Either<ApiError, Integer> delete(int i);

}
