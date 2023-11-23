package cliente.data.retrofit;

import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

import java.util.List;

public interface PersonajeApi {

    @GET("personaje")
    Single<List<Personaje>> getPersonajes();


}
