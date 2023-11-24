package servidor.jakarta.rest;

import domain.modelo.Personaje;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioPersonaje;
import servidor.jakarta.ConstantsJakarta;

import java.util.List;

@Log4j2
@Path("/personaje")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestPersonaje {

    private final ServicioPersonaje servicioPersonaje;

    @Inject
    public RestPersonaje(ServicioPersonaje servicioPersonaje) {
        this.servicioPersonaje = servicioPersonaje;
    }

    @GET
    public List<Personaje> getAll(){
        return servicioPersonaje.getAll().get();
    }

    @GET
    @Path(ConstantsJakarta.ID_PATH)
    public Personaje get(@PathParam(ConstantsJakarta.ID) String idParam){
        return servicioPersonaje.get(idParam).get();
    }

}
