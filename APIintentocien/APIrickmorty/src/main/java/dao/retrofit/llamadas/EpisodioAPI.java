package dao.retrofit.llamadas;

import dao.retrofit.modelo.episodios.ResponseEpisodio;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface EpisodioAPI {
    @GET("episode")
    Call<Map<String, Object>> getEpisodios();

}
