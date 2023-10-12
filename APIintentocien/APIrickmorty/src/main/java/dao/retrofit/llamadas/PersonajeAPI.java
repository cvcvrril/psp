package dao.retrofit.llamadas;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;

public interface PersonajeAPI {
    @GET("character")
    Call<Map<String, Object>> getPersonajes();

    @GET("character")
    Call<Map<String, Object>> getPersonajesGender(@Query("gender") String gender);

    @GET("character")
    Call<Map<String, Object>> getPersonajesStatus(@Query("status") String status);

    @GET("character/{id}")
    Call<Map<String, Object>> getPersonajesId(@Path("id") Integer id);

}
