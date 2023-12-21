package jakarta.rest;

import dao.modelo.Videojuego;
import domain.servicios.VideojuegoServicio;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path("/videojuegos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestVideojuegos {

    private final VideojuegoServicio videojuegoServicio;

    @Inject
    public RestVideojuegos(VideojuegoServicio videojuegoServicio) {
        this.videojuegoServicio = videojuegoServicio;
    }

    @GET
    public List<Videojuego> getAll(){
        return videojuegoServicio.getAll().get();
    }

}
