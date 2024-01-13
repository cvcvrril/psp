package dao.imp;

import dao.ConstantsDao;
import dao.interfaces.DaoPersonaje;
import dao.data.StaticLists;
import dao.modelo.Personaje;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class DaoPersonajeImp implements DaoPersonaje {

    @Override
    public Either<ApiError, List<Personaje>> getAll() {
        Either<ApiError, List<Personaje>> res;
        List<Personaje> personajeList;
        try {
            personajeList = StaticLists.listaPersonajes;
            res = Either.right(personajeList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> delete(int id) {
        Either<ApiError, Integer> res;
        Personaje personajeEliminar = StaticLists.listaPersonajes.stream().filter(personaje -> personaje.getId() == id).findFirst().orElse(null);
        if (StaticLists.listaPersonajes.remove(personajeEliminar)) {
            res = Either.right(1);
        } else {
            res = Either.left(new ApiError(ConstantsDao.HUBO_UN_ERROR_AL_ELIMINAR_EL_PERSONAJE, LocalDateTime.now()));
        }
        return res;
    }

    @Override
    public Either<ApiError, List<Personaje>> getIdVideojuego(int idVideojuego) {
        Either<ApiError, List<Personaje>> res;
        List<Personaje> personajeIdVideojuegoList;
        try {
            personajeIdVideojuegoList = StaticLists.listaPersonajes.stream().filter(personaje -> personaje.getIdVideojuego() == idVideojuego).toList();
            res = Either.right(personajeIdVideojuegoList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }
}
