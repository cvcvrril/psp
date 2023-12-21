package dao.imp;

import dao.ConstantsDAO;
import dao.DaoVideojuego;
import dao.data.StaticLists;
import dao.modelo.Videojuego;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class DaoVideojuegoImp implements DaoVideojuego {

    @Override
    public Either<ApiError, List<Videojuego>> getAll() {
        Either<ApiError, List<Videojuego>> res;
        List<Videojuego> videojuegoList;
        try {
            videojuegoList = StaticLists.listaVideojuegos;
            res = Either.right(videojuegoList);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

}
