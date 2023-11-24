package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import domain.modelo.Raza;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import servidor.dao.DaoFaccion;

import java.util.List;

public class ServicioFaccion {

    private final DaoFaccion daoFaccion;

    @Inject
    public ServicioFaccion(DaoFaccion daoFaccion) {
        this.daoFaccion = daoFaccion;
    }

    public Either<ApiError, List<Faccion>> getAll(){
        return daoFaccion.getAll();
    }
}
