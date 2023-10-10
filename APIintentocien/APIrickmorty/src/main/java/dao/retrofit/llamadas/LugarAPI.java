package dao.retrofit.llamadas;

import dao.retrofit.modelo.ResponsePersonaje;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface LugarAPI {

    @GET("location")
    Call<List<ResponsePersonaje>> getLugares();

}
