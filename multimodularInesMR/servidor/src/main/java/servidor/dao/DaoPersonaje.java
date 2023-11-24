package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import io.vavr.control.Either;

import java.util.List;

public interface DaoPersonaje {
    Either<ApiError, List<Personaje>> getAll();
    Either<ApiError, Personaje> get(int i);
    Either<ApiError, Integer> add(Personaje nuevoPersonaje);
    Either<ApiError, Integer> update(Personaje actualizadoPersonaje);
    Either<ApiError, Integer> delete(int i);
    Either<ApiError, List<Faccion>> getFaccionesByPersonaje(int idPersonaje);


}
