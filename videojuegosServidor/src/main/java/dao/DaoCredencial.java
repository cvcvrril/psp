package dao;

import dao.modelo.Credencial;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;

import java.util.List;

public interface DaoCredencial {

    Either<ApiError, Credencial> userLogged(Credencial credencial);
    Either<ApiError, Boolean> addCred(Credencial nuevoCredencial);
    Either<ApiError, Boolean> actualizarPassword(Credencial actualizadoCredencial);
    Either<ApiError, Credencial> getCredencialUser(String username);
    Either<ApiError, Credencial> getCredencialEmail(String email);
    Either<ApiError, Credencial> getCredencialCode(String email);
}
