package dao;

import dao.modelo.Credencial;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

public interface DaoCredencial {

    Either<ApiError, Credencial> userLogged(Credencial credencial);
    Either<ApiError, Boolean> addCred(Credencial nuevoCredencial);
    Either<ApiError, Boolean> actualizarPassword(Credencial actualizadoCredencial);

}
