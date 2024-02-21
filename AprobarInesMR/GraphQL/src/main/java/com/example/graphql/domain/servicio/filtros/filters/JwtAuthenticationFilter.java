package com.example.graphql.domain.servicio.filtros.filters;


import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer")) {

            String accessToken = authHeader.substring("Bearer".length()).trim();

            try {
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(clavePublicaKeyStore())
                        .build()
                        .parseClaimsJws(accessToken)
                        .getBody();
                String subject = claims.getSubject();
                String rol = (String) claims.get("rol");

                if (rol.equals("ROLE_ADMIN") || rol.equals("ROLE_USER")){
                    Authentication authentication = new UsernamePasswordAuthenticationToken(subject, null, AuthorityUtils.createAuthorityList(rol));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                }
            }catch (ExpiredJwtException ex){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }catch (JwtException e){
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }
        }
        filterChain.doFilter(request, response);
    }

    private PublicKey clavePublicaKeyStore() {
        String contra = "Jack";
        char[] password = contra.toCharArray();
        try (FileInputStream fis = new FileInputStream("keystore.pfx")) {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            keyStore.load(fis, password);
            if (keyStore.isKeyEntry("server")){
                Key key = keyStore.getKey("server", password);
                if (key instanceof PrivateKey){
                    X509Certificate certificate = (X509Certificate) keyStore.getCertificate("server");
                    return certificate.getPublicKey();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        throw new RuntimeException("Error al cargar la privateKey de la Keystore");
    }
}
