package cliente.data;

import cliente.data.retrofit.RegistroApi;
import cliente.domain.errores.ErrorC;
import com.google.gson.Gson;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DaoRegistro extends DaoGenerics{

    private final RegistroApi registroApi;

    @Inject
    public DaoRegistro(RegistroApi registroApi, Gson gson) {
        super(gson);
        this.registroApi = registroApi;
    }

    public Single<Either<ErrorC, Usuario>> add(Usuario nuevoUsuario){
        return safeSingleApicall(registroApi.add(nuevoUsuario));
    }

}
