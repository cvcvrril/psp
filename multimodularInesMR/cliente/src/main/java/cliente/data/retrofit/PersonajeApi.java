package cliente.data.retrofit;

import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface PersonajeApi {

    @GET("personaje")
    Single<List<Personaje>> getPersonajes();

    @GET("personaje/{id}")
    Single<Personaje> getPersonajeById(@Path("id") int id);

    @POST("personaje")
    Single<Personaje> addPersonaje(@Body Personaje personaje);

    @PUT("personaje")
    Single<Personaje> updatePersonaje(@Body Personaje personaje);

    @DELETE("personaje/{id}")
    Single<Response<Void>> deletePersonaje(@Path("id") int id);
}
