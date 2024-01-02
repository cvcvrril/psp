package jakarta.rest;

import dao.modelo.Videojuego;
import domain.servicios.VideojuegoServicio;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path("/videojuegos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DeclareRoles({"Admin", "User"})
public class RestVideojuegos {

    private final VideojuegoServicio videojuegoServicio;

    @Inject
    public RestVideojuegos(VideojuegoServicio videojuegoServicio) {
        this.videojuegoServicio = videojuegoServicio;
    }

    @GET
    @RolesAllowed({"Admin", "User"})
    public List<Videojuego> getAll(){
        return videojuegoServicio.getAll().get();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Integer deleteVideojuego(@PathParam("id") String idParam){
        return videojuegoServicio.delete(idParam).get();
    }
}
