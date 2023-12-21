package jakarta.rest;

import dao.modelo.Credencial;
import domain.servicios.CredencialServicio;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCredenciales {

    private final CredencialServicio servicio;

    @Inject
    public RestCredenciales(CredencialServicio servicio) {
        this.servicio = servicio;
    }

    @GET
    public Response getLogin(@QueryParam("username") String username, @QueryParam("password") String password){
        if (servicio.doLogin(new Credencial(0, username, password, "", true))){
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError("The username or the password are incorrect", LocalDateTime.now())).
                    build();
        }
    }

    @POST
    public Response doRegister(Credencial credencial){
        if (servicio.doRegister(credencial)){
            return Response.status(Response.Status.CREATED)
                    .entity(credencial).build();
        }else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError("The registration couldn't be completed", LocalDateTime.now())).build();
        }
    }
}
