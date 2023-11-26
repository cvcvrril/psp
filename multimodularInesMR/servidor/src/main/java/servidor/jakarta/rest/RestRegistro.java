package servidor.jakarta.rest;


import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import lombok.extern.log4j.Log4j2;
import servidor.domain.servicios.ServicioRegistro;

@Log4j2
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
