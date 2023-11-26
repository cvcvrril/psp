package cliente.data.retrofit;

import cliente.data.ConstantesData;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LoginApi {

    @GET(ConstantesData.LOGIN)
    Single<Usuario> getLogin(@Query(ConstantesData.USERNAME) String username, @Query(ConstantesData.PASSWORD) String password);
}
