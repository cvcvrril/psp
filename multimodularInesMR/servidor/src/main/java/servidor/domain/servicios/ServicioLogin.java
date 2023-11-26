package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servidor.dao.DaoLogin;

public class ServicioLogin {

    private final DaoLogin daoLogin;

    @Inject
    public ServicioLogin(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Either<ApiError, Usuario> check(String usuario){
        return daoLogin.check(usuario);
    }
}
