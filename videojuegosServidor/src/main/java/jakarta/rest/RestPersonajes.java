package jakarta.rest;

import dao.modelo.Personaje;
import domain.servicios.PersonajeServicio;
import jakarta.ConstantsJakarta;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path(ConstantsJakarta.PERSONAJES)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DeclareRoles({ConstantsJakarta.ROL_ADMIN, ConstantsJakarta.ROL_USER})
public class RestPersonajes {

    private final PersonajeServicio personajeServicio;

    @Inject
    public RestPersonajes(PersonajeServicio personajeServicio) {
        this.personajeServicio = personajeServicio;
    }

    @GET
    @RolesAllowed({ConstantsJakarta.ROL_ADMIN, ConstantsJakarta.ROL_USER})
    public List<Personaje> getAll(){
        return personajeServicio.getAll().get();
    }



    @DELETE
    @Path(ConstantsJakarta.PATH_ID)
    @RolesAllowed({ConstantsJakarta.ROL_ADMIN})
    public Integer deleteVideojuego(@PathParam(ConstantsJakarta.ID) String idParam){
        return personajeServicio.delete(idParam).get();
    }

    @GET
    @Path(ConstantsJakarta.PATH_ID_VIDEOJUEGO)
    @RolesAllowed({ConstantsJakarta.ROL_ADMIN, ConstantsJakarta.ROL_USER})
    public List<Personaje> getIdVideojuego(@PathParam(ConstantsJakarta.ID_VIDEOJUEGO) String idVideojuegoParam) {return personajeServicio.getIdVideojuego(idVideojuegoParam).get();}


}
