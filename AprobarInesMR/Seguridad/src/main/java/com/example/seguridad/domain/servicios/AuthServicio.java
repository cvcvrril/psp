package com.example.seguridad.domain.servicios;

import com.example.seguridad.data.modelo.request.AuthenticationRequest;
import com.example.seguridad.data.modelo.response.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServicio {

    private final PasswordEncoder passwordHash;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    public AuthServicio(PasswordEncoder passwordHash, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtService jwtService) {
        this.passwordHash = passwordHash;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtService = jwtService;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        var user = userDetailsService.loadUserByUsername(request.username());

        var jwtToken = jwtService.generateToken(user.getUsername(), 3000).get();
        var refreshToken = jwtService.generateRefreshToken(user.getUsername(), 48).get();
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

}
