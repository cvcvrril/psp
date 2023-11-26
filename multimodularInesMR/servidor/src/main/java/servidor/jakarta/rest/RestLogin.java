package servidor.jakarta.rest;


import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
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
    public Usuario getLogin(@QueryParam("usuario") String usuario, @QueryParam("pass") String pass){
        Usuario usuarioLogin = servicioLogin.login(usuario, pass).get();
        return usuarioLogin;
    }

}
