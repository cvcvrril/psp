package cliente.domain.usecases;

import cliente.data.DaoLogin;
import cliente.domain.errores.ErrorC;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class LoginUseCase {

    private final DaoLogin daoLogin;

    @Inject
    public LoginUseCase(DaoLogin daoLogin) {
        this.daoLogin = daoLogin;
    }

    public Single<Either<ErrorC, Usuario>> login(String username, String password){
        return daoLogin.getLogin(username, password);
    }

}
