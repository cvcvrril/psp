package dao.retrofit.llamadas;

import dao.retrofit.modelo.personajes.ResponsePersonaje;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface LugarAPI {

    @GET("location")
    Call<Map<String,Object>> getLugares();

}
