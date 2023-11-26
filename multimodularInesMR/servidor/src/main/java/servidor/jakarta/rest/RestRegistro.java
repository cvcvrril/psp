package servidor.jakarta.rest;


import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioRegistro;

@Log4j2
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestRegistro {

    private final ServicioRegistro servicioRegistro;

    @Inject
    public RestRegistro(ServicioRegistro servicioRegistro) {
        this.servicioRegistro = servicioRegistro;
    }

    @POST
    public Integer add(Usuario nuevoUsuario){
        return servicioRegistro.add(nuevoUsuario).get();
    }

}
