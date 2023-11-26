package cliente.data.retrofit;

import cliente.data.ConstantesData;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface PersonajeApi {

    @GET(ConstantesData.PERSONAJE)
    Single<List<Personaje>> getPersonajes();

    @GET(ConstantesData.PERSONAJE_ID)
    Single<Personaje> getPersonajeById(@Path(ConstantesData.ID) int id);

    @POST(ConstantesData.PERSONAJE)
    Single<Personaje> addPersonaje(@Body Personaje personaje);

    @PUT(ConstantesData.PERSONAJE)
    Single<Personaje> updatePersonaje(@Body Personaje personaje);

    @DELETE(ConstantesData.PERSONAJE_ID)
    Single<Response<Void>> deletePersonaje(@Path(ConstantesData.ID) int id);

    @DELETE(ConstantesData.PERSONAJE_DELETE_MULTIPLE)
    Single<Response<Void>> deletePersonajePorFaccion(@Path(ConstantesData.ID_FACCION)int idFaccion);
}
