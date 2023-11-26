package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Raza;
import io.vavr.control.Either;

import java.util.List;

public interface DaoRaza {

    Either<ApiError, List<Raza>>getAll();

}
