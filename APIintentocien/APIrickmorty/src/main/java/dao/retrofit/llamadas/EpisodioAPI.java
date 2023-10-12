package dao.retrofit.llamadas;

import common.Constantes;
import dao.retrofit.modelo.episodios.ResponseEpisodio;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;
import java.util.Map;

public interface EpisodioAPI {


    @GET(Constantes.EPISODE)
    Call<Map<String, Object>> getEpisodios();

}
