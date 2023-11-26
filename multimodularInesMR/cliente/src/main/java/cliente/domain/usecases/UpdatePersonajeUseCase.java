package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class UpdatePersonajeUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public UpdatePersonajeUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, Personaje>> updatePersonaje(Personaje personaje){
        return daoPersonaje.updatePersonaje(personaje);
    }
}
