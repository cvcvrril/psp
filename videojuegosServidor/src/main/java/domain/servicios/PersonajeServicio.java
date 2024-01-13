package domain.servicios;

import dao.interfaces.DaoPersonaje;
import dao.modelo.Personaje;
import domain.ConstantsDomain;
import domain.excepciones.BadArgumentException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class PersonajeServicio {

    private final DaoPersonaje daoPersonaje;

    @Inject
    public PersonajeServicio(DaoPersonaje daoPersonaje) {
        this.daoPersonaje = daoPersonaje;
    }

    public Either<ApiError, List<Personaje>> getAll(){
        return daoPersonaje.getAll();
    }

    public Either<ApiError, List<Personaje>> getIdVideojuego(String idVideojuegoParam){
        try {
            Integer idVideojuego = Integer.parseInt(idVideojuegoParam);
            return daoPersonaje.getIdVideojuego(idVideojuego);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDomain.ERROR_AL_METER_ALGUNO_DE_LOS_ARGUMENTOS);
        }
    }


    public Either<ApiError, Integer> delete(String idParam){
        try{
            Integer id = Integer.parseInt(idParam);
            return daoPersonaje.delete(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDomain.ERROR_AL_METER_ALGUNO_DE_LOS_ARGUMENTOS);
        }
    }

}
