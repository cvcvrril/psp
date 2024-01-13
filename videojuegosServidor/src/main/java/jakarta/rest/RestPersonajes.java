package jakarta.rest;

import dao.modelo.Personaje;
import domain.servicios.PersonajeServicio;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Path("/personajes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@DeclareRoles({"Admin", "User"})
public class RestPersonajes {

    private final PersonajeServicio personajeServicio;

    @Inject
    public RestPersonajes(PersonajeServicio personajeServicio) {
        this.personajeServicio = personajeServicio;
    }

    @GET
    @RolesAllowed({"Admin", "User"})
    public List<Personaje> getAll(){
        return personajeServicio.getAll().get();
    }



    @DELETE
    @Path("/{id}")
    @RolesAllowed({"Admin"})
    public Integer deleteVideojuego(@PathParam("id") String idParam){
        return personajeServicio.delete(idParam).get();
    }

    @GET
    @Path("/{idVideojuego}")
    @RolesAllowed({"Admin", "User"})
    public List<Personaje> getIdVideojuego(@PathParam("idVideojuego") String idVideojuegoParam) {return personajeServicio.getIdVideojuego(idVideojuegoParam).get();}


}
