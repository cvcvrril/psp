package dao.impl;

import dao.retrofit.llamadas.EpisodioAPI;
import domain.modelo.MiEpisodio;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DAOepisodio {

    /*Atributos*/

    private final EpisodioAPI episodioAPI;

    /*Constructor*/
    @Inject
    public DAOepisodio(EpisodioAPI episodioAPI) {
        this.episodioAPI = episodioAPI;
    }

    /*Métodos*/

    public Either<String, List<MiEpisodio>> llamadaRetrofit() {
        Either<String, List<MiEpisodio>> res;
        Response<Map<String, Object>> r;
        try {
            r = episodioAPI.getEpisodios().execute();
            if (r.isSuccessful()) {
                Map<String, Object> responseMap = r.body();
                List<Map<String, Object>> responseEpisodios = (List<Map<String, Object>>) responseMap.get("results");
                List<MiEpisodio> miEpisodios = responseEpisodios.stream()
                        .map(result -> new MiEpisodio(((Double) result.get("id")).intValue(), (String) result.get("name"), (String) result.get("episode")))
                        .toList();
                res = Either.right(miEpisodios);
            } else {
                r.errorBody().string();
                res = Either.left(r.message());
            }

        } catch (IOException e) {
            res = Either.left(e.getMessage());
        }
        return res;
    }


}
