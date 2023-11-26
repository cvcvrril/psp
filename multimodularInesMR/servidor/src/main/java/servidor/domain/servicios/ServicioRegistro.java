package servidor.domain.servicios;


import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.dao.DaoRegistro;

@Log4j2
public class ServicioRegistro {

    private final DaoRegistro daoRegistro;

    @Inject
    public ServicioRegistro(DaoRegistro daoRegistro) {
        this.daoRegistro = daoRegistro;
    }

    public Either<ApiError, Usuario>get(int id){
        return daoRegistro.get(id);
    }

    public Either<ApiError, Integer> add(Usuario nuevoUsuario){
        return daoRegistro.add(nuevoUsuario);
    }

}
