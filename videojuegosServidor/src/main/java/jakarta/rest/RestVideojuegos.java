package jakarta.rest;

import dao.modelo.Videojuego;
import domain.servicios.VideojuegoServicio;
import jakarta.ConstantsJakarta;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path(ConstantsJakarta.VIDEOJUEGOS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DeclareRoles({ConstantsJakarta.ROL_ADMIN, ConstantsJakarta.ROL_USER})
public class RestVideojuegos {

    private final VideojuegoServicio videojuegoServicio;

    @Inject
    public RestVideojuegos(VideojuegoServicio videojuegoServicio) {
        this.videojuegoServicio = videojuegoServicio;
    }

    @GET
    @RolesAllowed({ConstantsJakarta.ROL_ADMIN, ConstantsJakarta.ROL_USER})
    public List<Videojuego> getAll(){
        return videojuegoServicio.getAll().get();
    }

    @DELETE
    @Path(ConstantsJakarta.PATH_ID)
    @RolesAllowed({ConstantsJakarta.ROL_ADMIN})
    public Integer deleteVideojuego(@PathParam(ConstantsJakarta.ID) String idParam){
        return videojuegoServicio.delete(idParam).get();
    }
}
