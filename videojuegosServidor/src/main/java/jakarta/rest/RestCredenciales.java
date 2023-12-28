package jakarta.rest;

import dao.modelo.Credencial;
import domain.servicios.CredencialServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
    public Response getLogin(@QueryParam("username") String username, @QueryParam("password") String password, @Context HttpServletResponse response){
        if (servicio.doLogin(new Credencial(username, password, "", true))){
            String jwtToken = generarTokenJWT();

            // Adjuntar el token como un atributo en la respuesta
            response.addHeader("accessToken", jwtToken);
            return Response.status(Response.Status.NO_CONTENT).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError("El usuario o la contraseña son incorrectos", LocalDateTime.now())).
                    build();
        }
    }

    @SneakyThrows
    private String generarTokenJWT() {
        final MessageDigest digest =
                MessageDigest.getInstance("SHA-512");
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());

        String jws = Jwts.builder()
                .setSubject("servidor")
                .setIssuer("self")
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(60).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("user", "root")
                .signWith(keyConfig).compact();

        return jws;
    }

    @POST
    public Response doRegister(Credencial credencial){
        if (servicio.doRegister(credencial)){
            return Response.status(Response.Status.CREATED)
                    .entity(credencial).build();
        }else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError("El registro no pudo ser completado", LocalDateTime.now())).build();
        }
    }





}
