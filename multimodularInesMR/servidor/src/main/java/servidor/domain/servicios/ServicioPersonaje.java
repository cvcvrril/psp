package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Faccion;
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

    public Either<ApiError, Integer> add(Personaje nuevoPersonaje){
        validPersonaje(nuevoPersonaje.getNombre());
        return daoPersonaje.add(nuevoPersonaje);
    }

    private void validPersonaje(String nombrePersonaje){
        if (!Character.isUpperCase(nombrePersonaje.charAt(0))){
            throw new BadArgumentException("El nombre del personaje debe de empezar por mayúscula");
        }
    }

    public Either<ApiError, Integer> update(Personaje actualizadoPersonaje){
        return daoPersonaje.update(actualizadoPersonaje);
    }

    public Either<ApiError, Integer>delete(String idParam){
        try {
            Integer id = Integer.parseInt(idParam);
            return daoPersonaje.delete(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
        }
    }

    public Either<ApiError, Integer> deleteMultiple(String idFaccion){
        try {
            Integer id = Integer.parseInt(idFaccion);
            return daoPersonaje.deleteMultiple(id);
        } catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
        }
    }

    public Either<ApiError, List<Faccion>> getFaccionesByPersonaje(String idParam){
        try {
            Integer id = Integer.parseInt(idParam);
            return daoPersonaje.getFaccionesByPersonaje(id);
        } catch (NumberFormatException e) {
            log.error(e.getMessage(),e);
            throw new BadArgumentException(ConstantsDao.BAD_ARGUMENT_EXCEPTION);
        }
    }



}

