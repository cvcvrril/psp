package dao.retrofit.llamadas;

import common.Constantes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.Map;

public interface PersonajeAPI {


    @GET(Constantes.CHARACTER)
    Call<Map<String, Object>> getPersonajes();

    @GET(Constantes.CHARACTER)
    Call<Map<String, Object>> getPersonajesGender(@Query(Constantes.GENDER) String gender);

    @GET(Constantes.CHARACTER)
    Call<Map<String, Object>> getPersonajesStatus(@Query(Constantes.STATUS) String status);

    @GET(Constantes.CHARACTER_ID)
    Call<Map<String, Object>> getPersonajesId(@Path(Constantes.ID) Integer id);

}
