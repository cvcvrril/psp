package domain.usecase;

import dao.impl.DAOepisodio;
import domain.modelo.MiEpisodio;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.Data;

import java.util.List;

@Data
public class LoadEpisodioUsecase {

    /*Atributos*/


    private DAOepisodio daOepisodio;

    /*Constructor*/

    @Inject
    public LoadEpisodioUsecase(DAOepisodio daOepisodio) {
        this.daOepisodio = daOepisodio;
    }

    /*Métodos*/

    public Either<String, List<MiEpisodio>> llamadaRetrofit() {
        return daOepisodio.llamadaRetrofit().map(miEpisodios -> {
            if (miEpisodios.isEmpty()) {
                return List.of();
            } else {
                return miEpisodios;
            }
        });
    }


}
