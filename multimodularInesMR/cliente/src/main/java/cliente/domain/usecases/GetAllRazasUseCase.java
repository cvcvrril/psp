package cliente.domain.usecases;

import cliente.data.DaoRaza;
import cliente.domain.errores.ErrorC;
import domain.modelo.Raza;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class GetAllRazasUseCase {

    private final DaoRaza daoRaza;

    @Inject
    public GetAllRazasUseCase(DaoRaza daoRaza) {
        this.daoRaza = daoRaza;
    }

    public Single<Either<ErrorC, List<Raza>>> getAllRazas(){
        return daoRaza.getAllRazas();
    }
}
