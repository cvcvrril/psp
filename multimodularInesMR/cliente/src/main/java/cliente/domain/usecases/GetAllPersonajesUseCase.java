package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllPersonajesUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public GetAllPersonajesUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, List<Personaje>>> getAllPersonajes(){
        return daoPersonaje.getAllPersonajes();
    }

}
