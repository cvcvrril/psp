package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class DeletePersonajePorFaccionUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public DeletePersonajePorFaccionUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, String>> deletePersonajePorFaccion(int idFaccion){
        return daoPersonaje.deletePersonajePorFaccion(idFaccion);
    }
}
