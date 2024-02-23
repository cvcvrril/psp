package com.example.seguridad.ui.controllers;

import com.example.seguridad.data.modelo.request.AuthenticationRequest;
import com.example.seguridad.data.modelo.response.AuthenticationResponse;
import com.example.seguridad.domain.modelo.User;
import com.example.seguridad.domain.modelo.UserDTO;
import com.example.seguridad.domain.servicios.AuthServicio;
import com.example.seguridad.domain.servicios.UserServicio;
import com.example.seguridad.ui.exceptions.NullObjectException;
import com.example.seguridad.utils.Constantes;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(Constantes.REQUEST_MAPPING)
public class AuthController {

    private final AuthServicio servicioAuth;
    private final UserServicio servicioUser;

    @GetMapping(Constantes.MAPPING_LOGIN)
    public AuthenticationResponse loginAuth(@RequestBody AuthenticationRequest requestAuth) {
        return servicioAuth.authenticate(requestAuth);
    }

    @PostMapping(Constantes.MAPPING_REGISTRO)
    public UserDTO registroAuth(@RequestBody User newUser) {
        return servicioUser.add(newUser).getOrElseThrow(() -> new NullObjectException("User vacío"));
    }

}
