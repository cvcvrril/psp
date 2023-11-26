package cliente.domain.usecases;

import cliente.data.DaoPersonaje;
import cliente.domain.errores.ErrorC;
import domain.modelo.Personaje;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetPersonajeByIdUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public GetPersonajeByIdUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Single<Either<ErrorC, Personaje>> getAllPersonajes(int id){
        return daoPersonaje.getPersonajeById(id);
    }

}
