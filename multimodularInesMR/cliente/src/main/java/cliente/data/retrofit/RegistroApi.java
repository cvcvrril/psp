package cliente.data.retrofit;

import cliente.data.ConstantesData;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistroApi {

    @POST(ConstantesData.USUARIO)
    Single<Usuario> add(@Body Usuario nuevoUsuario);

}
