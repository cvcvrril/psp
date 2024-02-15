package com.example.seguridad.controllers;

import com.example.seguridad.domain.modelo.User;
import com.example.seguridad.domain.servicios.UserServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServicio servicio;

    @PostMapping("/registro")
    public User registro(@RequestBody User newUser){
        return servicio.add(newUser).get();
    }

    @PostMapping("/login")
    public User login(@RequestBody User loginUser){
        return servicio.login(loginUser);
    }

}
