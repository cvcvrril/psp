package domain.servicios;

import dao.interfaces.DaoVideojuego;
import dao.modelo.Videojuego;
import domain.excepciones.BadArgumentException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class VideojuegoServicio {

    private final DaoVideojuego daoVideojuego;

    @Inject
    public VideojuegoServicio(DaoVideojuego daoVideojuego) {
        this.daoVideojuego = daoVideojuego;
    }

    public Either<ApiError, List<Videojuego>> getAll(){
        return daoVideojuego.getAll();
    }

    public Either<ApiError, Integer> delete(String idParam){
        try{
            Integer id = Integer.parseInt(idParam);
            return daoVideojuego.delete(id);
        }catch (NumberFormatException e){
            log.error(e.getMessage(),e);
            throw new BadArgumentException("Error al meter alguno de los argumentos");
        }
    }

}
