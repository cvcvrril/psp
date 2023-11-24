package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.FaccionPersonaje;
import io.vavr.control.Either;

import java.util.List;

public interface DaoFaccionPersonaje {

    Either<ApiError, List<FaccionPersonaje>> getAll();
    Either<ApiError, FaccionPersonaje> get(int idPersonaje);
    Either<ApiError, Integer> add(FaccionPersonaje nuevaRelacion);
    Either<ApiError, Integer> update(FaccionPersonaje actualizadaRelacion);
    Either<ApiError, Integer> delete(int idPersonaje);

}
