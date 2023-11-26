package servidor.jakarta.rest;


import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import servidor.domain.servicios.ServicioLogin;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private final ServicioLogin servicioLogin;

    @Inject
    public RestLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @GET
    public Usuario getLogin(){
        return servicioLogin.check(getLogin().getUsername()).get();
    }

}
