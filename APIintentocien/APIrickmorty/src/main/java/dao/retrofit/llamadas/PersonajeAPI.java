package dao.retrofit.llamadas;

import dao.retrofit.modelo.ResponsePersonaje;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PersonajeAPI {
    @GET("character")
    Call<List<ResponsePersonaje>> getPersonajes();


}
