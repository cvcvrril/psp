package jakarta.rest;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import dao.modelo.Credencial;
import domain.excepciones.WrongObjectException;
import domain.servicios.AuthorizacionRespone;
import domain.servicios.CredencialServicio;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ConstantsJakarta;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Log4j2
@Path(ConstantsJakarta.LOGIN)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCredenciales {

    private final CredencialServicio servicio;

    @Inject
    public RestCredenciales(CredencialServicio servicio) {
        this.servicio = servicio;
    }

    @GET
    public Response getLogin(@QueryParam(ConstantsJakarta.USERNAME) String username, @QueryParam(ConstantsJakarta.PASSWORD) String password){
        if (servicio.doLogin(new Credencial(username, password, ConstantsJakarta.EMPTY, true, ConstantsJakarta.EMPTY, ConstantsJakarta.EMPTY))){
            Credencial inicioSesion = servicio.getCredencialUser(username);
            String jwtAToken = generarTokenJWT(120, inicioSesion.getUser(), inicioSesion.getRol());
            String jwtRToken = generarTokenJWT(1800, inicioSesion.getUser(), inicioSesion.getRol());
            AuthorizacionRespone auth = new AuthorizacionRespone(jwtAToken,jwtRToken);
            return Response.status(Response.Status.OK).entity(auth).build();
        }else {
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ApiError(ConstantsJakarta.EL_USUARIO_O_LA_CONTRASENA_SON_INCORRECTOS, LocalDateTime.now())).
                    build();
        }
    }

    @POST
    public Response doRegister(Credencial credencial){
        if (servicio.doRegister(credencial)){
            servicio.mandarMail(credencial.getEmail());
            return Response.status(Response.Status.CREATED)
                    .entity(credencial).build();
        }else {
            return Response.status(Response.Status.CONFLICT)
                    .entity(new ApiError(ConstantsJakarta.EL_REGISTRO_NO_PUDO_SER_COMPLETADO, LocalDateTime.now())).build();
        }
    }

    @Path(ConstantsJakarta.FORGOTPASSWORD)
    @POST
    public Response mandarCorreoPassword(Credencial credencial){
        if (credencial.getEmail() != null){
            servicio.mandarMailCambioPassword(credencial.getEmail());
            return Response.status(Response.Status.OK)
                    .entity(ConstantsJakarta.CORREO_ENVIADO).build();
        }else {
            throw new WrongObjectException(ConstantsJakarta.EL_CORREO_NO_ES_VALIDO);
        }
    }

    @Path(ConstantsJakarta.REFRESHTOKEN)
    @PUT
    public Response actualizarTokenAcceso(@Context HttpServletResponse response, @Context HttpServletRequest request){
        String refreshToken = request.getHeader(ConstantsJakarta.REFRESH_TOKEN);
        JWTClaimsSet claimsSet;
        try {

            claimsSet = JWTParser.parse(refreshToken).getJWTClaimsSet();
            String username = claimsSet.getSubject();
            String roles = claimsSet.getStringClaim(ConstantsJakarta.ROL);

            String newAccessToken =  generarTokenJWT(120, username, roles);

            response.setHeader(HttpHeaders.AUTHORIZATION, newAccessToken);
            return Response.ok().entity(newAccessToken).build();
        } catch (ParseException e) {
            throw new WrongObjectException(ConstantsJakarta.HA_HABIDO_UN_PROBLEMA_AL_GENERAR_EL_NUEVO_ACCESS_TOKEN);
        }
    }


    private String generarTokenJWT(int expirationSeconds, String username, String rol) {
        final MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(ConstantsJakarta.ALGORITHM_SHA_512);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        digest.update(ConstantsJakarta.CLAVE.getBytes(StandardCharsets.UTF_8));
        final SecretKeySpec key2 = new SecretKeySpec(
                digest.digest(), 0, 64, ConstantsJakarta.ALGORITHM_AES);
        SecretKey keyConfig = Keys.hmacShaKeyFor(key2.getEncoded());

        return Jwts.builder()
                .setSubject(username)
                .setIssuer(ConstantsJakarta.ISSUER_SELF)
                .setExpiration(Date
                        .from(LocalDateTime.now().plusSeconds(expirationSeconds).atZone(ZoneId.systemDefault())
                                .toInstant()))
                .claim(ConstantsJakarta.ROL, rol)
                .signWith(keyConfig).compact();
    }


}
