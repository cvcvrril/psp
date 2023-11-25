package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class AddPersonajeUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public AddPersonajeUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, Personaje>> addPerdonaje(Personaje personaje){
        return daoPersonaje.addPersonaje(personaje);
    }
}
