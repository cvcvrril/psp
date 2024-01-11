package jakarta.rest;

import dao.modelo.Credencial;
import domain.excepciones.WrongObjectException;
import domain.servicios.CredencialServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
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
        if (servicio.doLogin(new Credencial(username, password, "", true, "", ""))){
            Credencial inicioSesion = servicio.getCredencialUser(username);
            String jwtAToken = generarTokenJWT(120, inicioSesion.getUser(), inicioSesion.getRol());
            String jwtRToken = generarTokenJWT(1800, inicioSesion.getUser(), inicioSesion.getRol());
            response.addHeader("Authorization", "Bearer " + jwtAToken );
            response.addHeader("RefreshToken", jwtRToken);
            return Response.status(Response.Status.OK).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError("El usuario o la contraseña son incorrectos", LocalDateTime.now())).
                    build();
        }
    }

    //TODO: METER ESTO EN OTRO LADO [

    @SneakyThrows
    private String generarTokenJWT(int expirationSeconds, String username, String rol) {
        final MessageDigest digest =
                MessageDigest.getInstance("SHA-512");
        digest.update("clave".getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, "AES");
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());

        return Jwts.builder()
                .setSubject(username)
                .setIssuer("self")
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(expirationSeconds).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim("rol", rol)
                .signWith(keyConfig).compact();
    }

    //TODO: METER ESTO EN OTRO LADO ]

    @POST
    public Response doRegister(Credencial credencial){
        if (servicio.doRegister(credencial)){
            servicio.mandarMail(credencial.getEmail());
            return Response.status(Response.Status.CREATED)
                    .entity(credencial).build();
        }else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError("El registro no pudo ser completado", LocalDateTime.now())).build();
        }
    }

    @Path("/forgotPassword")
    @POST
    public Response mandarCorreoPassword(Credencial credencial){
        if (credencial.getEmail() != null){
            servicio.mandarMailCambioPassword(credencial.getEmail());
            return Response.status(Response.Status.OK)
                    .entity("Correo enviado").build();
        }else {
            throw new WrongObjectException("El correo no es válido");
        }

    }

    //TODO: TENGO QUE ARREGLAR LO DEL REFRESH TOKEN -> INVESTIGAR SI ES MEJOR HACERLO CON SERVLETS O CON LLAMADA DESDE EL REST

    @Path("/refreshToken")
    @PUT
    public Response actualizarTokenAcceso(){

        return null;
    }
}
