package jakarta.rest;

import dao.modelo.Personaje;
import domain.servicios.PersonajeServicio;
import jakarta.annotation.security.DeclareRoles;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
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


}
