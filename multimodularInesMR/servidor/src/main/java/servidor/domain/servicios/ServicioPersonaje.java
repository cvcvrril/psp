package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.dao.DaoPersonaje;

import java.util.List;

@Log4j2
public class ServicioPersonaje {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public ServicioPersonaje(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Either<ApiError, List<Personaje>> getAll(){
        return daoPersonaje.getAll();
    }

}

