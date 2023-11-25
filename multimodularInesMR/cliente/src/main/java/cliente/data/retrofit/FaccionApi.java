package cliente.data.retrofit;

import domain.modelo.Faccion;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import java.util.List;

public interface FaccionApi {

    @GET("personaje")
    Single<List<Faccion>> getFacciones();

}
