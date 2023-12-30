package domain.servicios;

import dao.DaoPersonaje;
import dao.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class PersonajeServicio {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public PersonajeServicio(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Either<ApiError, List<Personaje>> getAll(){
        return daoPersonaje.getAll();
    }
}
