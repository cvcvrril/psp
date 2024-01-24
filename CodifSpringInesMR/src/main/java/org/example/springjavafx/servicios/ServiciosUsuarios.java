package org.example.springjavafx.servicios;

import org.example.springjavafx.data.UserRepository;
import org.example.springjavafx.data.modelo.User;
import org.springframework.stereotype.Service;

@Service
public class ServiciosUsuarios {

    private final UserRepository repository;


    public ServiciosUsuarios(UserRepository repository) {
        this.repository = repository;
    }

    public String getNombreUsuario() {
        return "Usuario";
    }

    public void addUser(User nuevoUsuario){
        repository.save(nuevoUsuario);
    }

}
