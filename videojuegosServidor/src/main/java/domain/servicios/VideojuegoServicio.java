package domain.servicios;

import dao.DaoVideojuego;
import dao.modelo.Videojuego;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;

import java.util.List;

public class VideojuegoServicio {

    private final DaoVideojuego daoVideojuego;

    @Inject
    public VideojuegoServicio(DaoVideojuego daoVideojuego) {
        this.daoVideojuego = daoVideojuego;
    }

    public Either<ApiError, List<Videojuego>> getAll(){
        return daoVideojuego.getAll();
    }
}
