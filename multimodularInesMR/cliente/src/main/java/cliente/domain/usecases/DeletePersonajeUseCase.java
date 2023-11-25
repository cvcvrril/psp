package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DeletePersonajeUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public DeletePersonajeUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, String>> deletePersonaje(int id){
        return daoPersonaje.deletePersonaje(id);
    }

}
