package cliente.domain.usecases;

import cliente.data.DaoFaccion;
import cliente.domain.errores.ErrorC;
import domain.modelo.Faccion;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllFaccionesUseCase {

    private final DaoFaccion daoFaccion;

    @Inject
    public GetAllFaccionesUseCase(DaoFaccion daoFaccion) {
        this.daoFaccion = daoFaccion;
    }

    public Single<Either<ErrorC, List<Faccion>>> getAllFacciones(){
        return daoFaccion.getAllFacciones();
    }
}
