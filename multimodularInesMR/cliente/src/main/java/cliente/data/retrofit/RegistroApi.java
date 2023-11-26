package cliente.data.retrofit;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegistroApi {
    @POST("usuario")
    Single<Usuario> add(@Body Usuario nuevoUsuario);

}
