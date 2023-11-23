package cliente.data.usecases;

import cliente.data.DaoPersonaje;
import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllPersonajesUseCase {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public GetAllPersonajesUseCase(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Either<ApiError, List<Personaje>> getAllPersonajes(){
        return (Either<ApiError, List<Personaje>>) daoPersonaje.getAllPersonajes();
    }

}
