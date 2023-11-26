package servidor.jakarta.rest;


import domain.modelo.Usuario;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import servidor.domain.servicios.ServicioLogin;
import servidor.jakarta.ConstantsJakarta;

@Path(ConstantsJakarta.ROOT_LOGIN)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private final ServicioLogin servicioLogin;
    @Context
    private HttpServletRequest request;

    @Inject
    public RestLogin(ServicioLogin servicioLogin) {
        this.servicioLogin = servicioLogin;
    }

    @GET
    public Usuario getLogin(@QueryParam(ConstantsJakarta.USUARIO) String usuario, @QueryParam(ConstantsJakarta.PASS) String pass){
        Usuario usuarioLogin = servicioLogin.login(usuario, pass).get();
        request.getSession().setAttribute(ConstantsJakarta.LOGIN, true);
        return usuarioLogin;
    }

}
