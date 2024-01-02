package dao.imp;

import dao.ConstantsDAO;
import dao.DaoVideojuego;
import dao.data.StaticLists;
import dao.modelo.Personaje;
import dao.modelo.Videojuego;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
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

    @Override
    public Either<ApiError, Integer> delete(Integer id) {
        Either<ApiError, Integer> res;
        Videojuego videojuegoEliminar = StaticLists.listaVideojuegos.stream().filter(videojuego -> videojuego.getId() == id).findFirst().orElse(null);
        if (StaticLists.listaVideojuegos.remove(videojuegoEliminar)){
            //Personaje personajeEliminar = StaticLists.listaPersonajes.stream().filter(personaje -> personaje.getIdVideojuego() == id).findFirst().orElse(null);
            List<Personaje> personajesEliminar = StaticLists.listaPersonajes.stream().filter(personaje -> personaje.getIdVideojuego() == id).toList();
            StaticLists.listaPersonajes.removeAll(personajesEliminar);
            res = Either.right(1);
        } else {
            res = Either.left(new ApiError("Hubo un error al eliminar el videojuego", LocalDateTime.now()));
        }
        return res;
    }

}
