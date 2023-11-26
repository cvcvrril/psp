package servidor.dao;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;

public interface DaoRegistro {

    Either<ApiError, Usuario> get(int id);
    Either<ApiError, Integer> add(Usuario nuevoUsuario);

}
