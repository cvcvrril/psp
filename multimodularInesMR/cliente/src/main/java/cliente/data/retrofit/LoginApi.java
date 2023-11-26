package cliente.data.retrofit;

import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {
    @GET("login")
    Single<Usuario> getLogin(@Query("username") String username, @Query("password") String password);
}
