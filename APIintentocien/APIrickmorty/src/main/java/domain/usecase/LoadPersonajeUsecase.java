package domain.usecase;

import dao.impl.DAOpersonaje;
import domain.modelo.MiPersonaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.Data;

import java.util.List;

@Data
public class LoadPersonajeUsecase {

    /*Atributos*/
    private DAOpersonaje daOpersonaje;

    /*Constructor*/
    @Inject
    public LoadPersonajeUsecase(DAOpersonaje daOpersonaje) {
        this.daOpersonaje = daOpersonaje;
    }

    /*Métodos*/
    public Either<String, List<MiPersonaje>> llamadaRetrofit() {
        return daOpersonaje.llamadaRetrofit().map(miPersonajes -> {
            if (miPersonajes.isEmpty()) {
                return List.of();
            } else {
                return miPersonajes;
            }
        });
    }


}
