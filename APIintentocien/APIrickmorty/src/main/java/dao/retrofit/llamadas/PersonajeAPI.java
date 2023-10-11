package dao.retrofit.llamadas;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface PersonajeAPI {
    @GET("character")
    Call<Map<String, Object>> getPersonajes();


}
