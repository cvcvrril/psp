package cliente.data;

import cliente.data.retrofit.LoginApi;
import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DaoLogin extends DaoGenerics{

    private final LoginApi loginApi;

    @Inject
    public DaoLogin(LoginApi loginApi, Gson gson) {
        super(gson);
        this.loginApi = loginApi;
    }

    public Single<Either<ErrorC, Usuario>> getLogin(String username, String pass){
        return safeSingleApicall(loginApi.getLogin(username, pass));
    }

}
