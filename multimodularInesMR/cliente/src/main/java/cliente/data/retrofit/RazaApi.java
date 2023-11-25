package cliente.data.retrofit;

import domain.modelo.Raza;
import retrofit2.http.GET;
import io.reactivex.rxjava3.core.Single;

import java.util.List;

public interface RazaApi {


    @GET("raza")
    Single<List<Raza>> getRazas();

}
