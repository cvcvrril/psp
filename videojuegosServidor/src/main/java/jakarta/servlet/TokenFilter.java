package jakarta.servlet;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import jakarta.inject.Inject;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.credential.BasicAuthenticationCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Provider
@WebFilter("regex(/api/((?!login).)*)")
public class TokenFilter implements HttpAuthenticationMechanism {

    private final InMemoryIdentityStore identity;

    @Inject
    public TokenFilter(InMemoryIdentityStore identity) {
        this.identity = identity;
    }

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse
            , HttpMessageContext httpMessageContext) throws AuthenticationException {
        CredentialValidationResult c = CredentialValidationResult.INVALID_RESULT;
        if (!httpMessageContext.isProtected())
            return httpMessageContext.doNothing();

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] valores = header.split(" ");

            if (valores[0].equalsIgnoreCase("Bearer")) {
                String accessToken = valores[1];

                // Validar el token de acceso
                c = validarTokenDeAcceso(accessToken);

                //c = identity.validate(new BasicAuthenticationCredential(valores[1]));

                if (c.getStatus() == CredentialValidationResult.Status.VALID) {
                    httpServletRequest.getSession().setAttribute("USERLOGIN", c);
                    // Generar token
                    // Añadir al response
                }
            } else if (valores[0].equalsIgnoreCase("Logout")) {
                httpServletRequest.getSession().removeAttribute("USERLOGIN");
                httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
            }

        } else {
            if (httpServletRequest.getSession().getAttribute("USERLOGIN") != null) {
                c = (CredentialValidationResult) httpServletRequest.getSession().getAttribute("USERLOGIN");
            }
        }

        if (!c.getStatus().equals(CredentialValidationResult.Status.VALID)) {
            httpServletRequest.setAttribute("status", c.getStatus());
            return httpMessageContext.doNothing();
        }


        return httpMessageContext.notifyContainerAboutLogin(c);
    }

    private CredentialValidationResult validarTokenDeAcceso(String accessToken) {
        try {
            // Parsear el token JWT
            JWTClaimsSet claimsSet = JWTParser.parse(accessToken).getJWTClaimsSet();

            // Validar cualquier condición adicional que necesites
            // (puede depender de tu lógica de negocio y de los datos almacenados en el token)

            // Obtener información del token (por ejemplo, roles y nombre de usuario)
            String username = claimsSet.getSubject();
            String roles = claimsSet.getStringClaim("rol");

            // Realizar cualquier otra validación necesaria

            // Devolver un CredentialValidationResult válido con roles y nombre de usuario
            return new CredentialValidationResult(username, Collections.singleton(roles));
        } catch (ParseException e) {
            // El token no pudo ser parseado, considerarlo inválido
            e.printStackTrace(); // Tratar de loggear o manejar el error de forma apropiada
            return CredentialValidationResult.INVALID_RESULT;
        }

    }

    @Override
    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return HttpAuthenticationMechanism.super.secureResponse(request, response, httpMessageContext);
    }

    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
        request.getSession().removeAttribute("USERLOGIN");
        request.getSession().removeAttribute("accessToken");
        request.getSession().removeAttribute("refreshToken");
    }

}
