package jakarta.servlet;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.ConstantsJakarta;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;

@Log4j2
@Provider

public class TokenFilter implements HttpAuthenticationMechanism {

    @Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest,
                                                HttpServletResponse httpServletResponse
            , HttpMessageContext httpMessageContext) throws AuthenticationException {
        CredentialValidationResult c = CredentialValidationResult.INVALID_RESULT;
        if (!httpMessageContext.isProtected())
            return httpMessageContext.doNothing();

        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null) {
            String[] valores = header.split(ConstantsJakarta.EMPTY);

            if (valores[0].equalsIgnoreCase(ConstantsJakarta.BEARER)) {
                String accessToken = valores[1];

                c = validarTokenDeAcceso(accessToken);

                if (c.getStatus() == CredentialValidationResult.Status.VALID) {
                    httpServletRequest.getSession().setAttribute(ConstantsJakarta.USERLOGIN, c);
                }
            }
        }
        if (!c.getStatus().equals(CredentialValidationResult.Status.VALID)) {
            httpServletRequest.setAttribute(ConstantsJakarta.STATUS, c.getStatus());
            return httpMessageContext.doNothing();
        }
        return httpMessageContext.notifyContainerAboutLogin(c);
    }

    private CredentialValidationResult validarTokenDeAcceso(String accessToken) {
        try {
            JWTClaimsSet claimsSet = JWTParser.parse(accessToken).getJWTClaimsSet();

            String username = claimsSet.getSubject();
            String roles = claimsSet.getStringClaim(ConstantsJakarta.ROL);

            Date expirationDate = claimsSet.getExpirationTime();
            LocalDateTime expiration = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            LocalDateTime now = LocalDateTime.now();

            if (expiration != null && now.isAfter(expiration)) {
                log.warn(ConstantsJakarta.EL_TOKEN_DE_ACCESO_HA_EXPIRADO_SE_VA_A_GENERAR_OTRO);
                return CredentialValidationResult.INVALID_RESULT;
            }
            return new CredentialValidationResult(username, Collections.singleton(roles));
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return CredentialValidationResult.INVALID_RESULT;
        }
    }

    @Override
    public AuthenticationStatus secureResponse(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) throws AuthenticationException {
        return HttpAuthenticationMechanism.super.secureResponse(request, response, httpMessageContext);
    }

    @Override
    public void cleanSubject(HttpServletRequest request, HttpServletResponse response, HttpMessageContext httpMessageContext) {
        request.getSession().removeAttribute(ConstantsJakarta.USERLOGIN);
        request.getSession().removeAttribute(ConstantsJakarta.AUTHORIZATION);
        request.getSession().removeAttribute(ConstantsJakarta.REFRESH_TOKEN);
    }

}
