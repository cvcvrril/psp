package com.example.ej1practicaseguridad.ui;

import com.example.ej1practicaseguridad.data.modelo.EmpleadoEntity;
import com.example.ej1practicaseguridad.domain.servicios.EmpleadoService;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

import java.util.Scanner;

public class LoginController {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SeContainerInitializer seContainerInitializer = SeContainerInitializer.newInstance();
        final SeContainer container = seContainerInitializer.initialize();
        EmpleadoService service = container.select(EmpleadoService.class).get();
        EmpleadoEntity empleado = new EmpleadoEntity();
        String username;
        String password;
        System.out.println("Introduzca el username del empleado:");
        do {
            username = scanner.next();
            empleado.setUsername(username);
        } while (username.isEmpty());
        System.out.println("Introduzca el password del empleado:");
        do {
            password = scanner.next();
            empleado.setPassword(password);
        } while (password.isEmpty());
        empleado.setId(0);
        service.doRegistro(empleado);
    }

}
