package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import io.vavr.control.Either;

import java.util.List;

public interface DaoFaccion {

    Either<ApiError, List<Faccion>> getAll();
    Either<ApiError, List<Faccion>> get(int id);
}
