package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Raza;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servidor.dao.DaoRaza;

import java.util.List;

public class ServicioRaza {

    private final DaoRaza daoRaza;

    @Inject
    public ServicioRaza(DaoRaza daoRaza) {
        this.daoRaza = daoRaza;
    }

    public Either<ApiError, List<Raza>> getAll(){
        return daoRaza.getAll();
    }

}
