package servidor.jakarta.rest;


import domain.modelo.Faccion;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioFaccion;
import servidor.jakarta.ConstantsJakarta;

import java.util.List;

@Log4j2
@Path(ConstantsJakarta.ROOT_FACCION)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestFaccion {

    private final ServicioFaccion servicioFaccion;

    @Inject
    public RestFaccion(ServicioFaccion servicioFaccion) {
        this.servicioFaccion = servicioFaccion;
    }

    @GET
    public List<Faccion> getAll(){
        return servicioFaccion.getAll().get();
    }
}
