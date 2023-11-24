package servidor.jakarta.rest;

import domain.modelo.Faccion;
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

    @GET
    @Path("/{id}/facciones")
    public List<Faccion> getFaccionesByPersonaje(@PathParam(ConstantsJakarta.ID) String idParam){
        return servicioPersonaje.getFaccionesByPersonaje(idParam).get();
    }

    @PUT
    public Integer update(Personaje actualizadoPersonaje){
        return servicioPersonaje.update(actualizadoPersonaje).get();
    }

    @POST
    public Integer add(Personaje nuevoPersonaje){
        return servicioPersonaje.add(nuevoPersonaje).get();
    }

    @DELETE
    @Path(ConstantsJakarta.ID_PATH)
    public Integer delete(@PathParam(ConstantsJakarta.ID) String idParam){
        return servicioPersonaje.delete(idParam).get();
    }

    @DELETE
    @Path("/delete-multiple/{idFaccion}")
    public Integer deleteMultiple(@PathParam("idFaccion") String idFaccion){
        return servicioPersonaje.deleteMultiple(idFaccion).get();
    }

}
