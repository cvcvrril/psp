package cliente.data.retrofit;

import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface PersonajeApi {

    @GET("personaje")
    Single<List<Personaje>> getPersonajes();

    @POST("personaje")
    Single<Personaje> createPersonaje(@Body Personaje personaje);

    @PUT("personaje/{id}")
    Single<Personaje> updatePersonaje(@Path("id") int id, @Body Personaje personaje);

    @DELETE("personaje/{id}")
    Single<Response<Void>> deletePersonaje(@Path("id") int id);
}
