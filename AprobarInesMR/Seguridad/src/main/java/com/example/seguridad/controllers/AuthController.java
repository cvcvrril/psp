package com.example.seguridad.controllers;

import com.example.seguridad.data.modelo.request.AuthenticationRequest;
import com.example.seguridad.data.modelo.response.AuthenticationResponse;
import com.example.seguridad.domain.modelo.User;
import com.example.seguridad.domain.servicios.AuthServicio;
import com.example.seguridad.domain.servicios.UserServicio;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthServicio servicioAuth;
    private final UserServicio servicioUser;

    @GetMapping("/login")
    public AuthenticationResponse loginAuth(@RequestBody AuthenticationRequest requestAuth) {
        return servicioAuth.authenticate(requestAuth);
    }

    @PostMapping("/registro")
    public User registroAuth(@RequestBody User newUser) {
        return servicioUser.add(newUser).get();
    }

}
