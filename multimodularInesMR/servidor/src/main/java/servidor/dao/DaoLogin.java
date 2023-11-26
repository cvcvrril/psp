package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;

public interface DaoLogin {

    Either<ApiError, Usuario> check(String usuario);

}
