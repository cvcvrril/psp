package dao.imp;

import dao.ConstantsDAO;
import dao.DaoPersonaje;
import dao.data.StaticLists;
import dao.modelo.Personaje;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

import java.util.List;

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
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Integer> delete(int id) {
        return null;
    }
}
