package servidor.jakarta.rest;

import domain.modelo.Raza;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioRaza;
import servidor.jakarta.ConstantsJakarta;

import java.util.List;

@Log4j2
@Path(ConstantsJakarta.ROOT_RAZA)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRaza {


    private final ServicioRaza servicioRaza;

    @Inject
    public RestRaza(ServicioRaza servicioRaza) {
        this.servicioRaza = servicioRaza;
    }

    @GET
    public List<Raza> getAll(){
        return servicioRaza.getAll().get();
    }

}
