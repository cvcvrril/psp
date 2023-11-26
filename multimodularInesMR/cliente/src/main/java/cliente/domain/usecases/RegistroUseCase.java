package cliente.domain.usecases;

import cliente.data.DaoRegistro;
import cliente.domain.errores.ErrorC;
import domain.modelo.Usuario;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class RegistroUseCase {

    private final DaoRegistro daoRegistro;

    @Inject
    public RegistroUseCase(DaoRegistro daoRegistro) {
        this.daoRegistro = daoRegistro;
    }

    public Single<Either<ErrorC, Usuario>> add(Usuario nuevoUsuario){
        return daoRegistro.add(nuevoUsuario);
    }
}
