package domain.usecase;

import dao.impl.DAOlugar;
import domain.modelo.MiLugar;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.Data;

import java.util.List;

@Data
public class LoadLugarUsecase {

    /*Atributos*/
    private DAOlugar daOlugar;
    /*Constructor*/
    @Inject
    public LoadLugarUsecase(DAOlugar daOlugar) {
        this.daOlugar = daOlugar;
    }

    /*Métodos*/

    public Either<String, List<MiLugar>> llamadaRetrofit() {
        return daOlugar.llamadaRetrofit().map(miLugares -> {
            if (miLugares.isEmpty()) {
                return List.of();
            } else {
                return miLugares;
            }
        });
    }


}
