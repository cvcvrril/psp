package servidor.jakarta.rest;

import domain.modelo.Personaje;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioPersonaje;

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

}
