package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import servidor.dao.ConstantsDao;
import servidor.dao.DaoPersonaje;
import servidor.domain.modelo.excepciones.BadArgumentException;

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

    public Either<ApiError, Personaje> get(String idParam){
        try {
            Integer id = Integer.parseInt(idParam);
            return daoPersonaje.get(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
        }

    }

}

