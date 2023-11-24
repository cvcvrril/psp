package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Raza;
import io.vavr.control.Either;

import java.util.List;

public interface DaoRaza {

    Either<ApiError, List<Raza>>getAll();
    Either<ApiError, Raza> get(int id);
    Either<ApiError, Integer> add(Raza nuevaRaza);
    Either<ApiError, Integer> update(Raza actualizadaRaza);
    Either<ApiError, Integer> delete(int id);

}
