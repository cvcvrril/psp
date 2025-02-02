package com.example.graphqlserverjavainesmr.domain.servicio;

import com.example.graphqlserverjavainesmr.data.repositorios.UserRepository;
import com.example.graphqlserverjavainesmr.domain.modelo.Rol;
import com.example.graphqlserverjavainesmr.domain.modelo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServicio {

    private final UserRepository repository;

    public UserServicio(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll(){
        return repository.getAllWithPermisos().stream().map(
                userEntity -> {
                    List<Rol> roles = userEntity.getRoles().stream().map(
                        rolEntity ->
                                new Rol(Math.toIntExact(rolEntity.getId()),
                                        rolEntity.getRol()
                                )
                    ).toList();
                    return new User(Math.toIntExact(userEntity.getId()),
                            userEntity.getUsername(),
                            userEntity.getPassword(),
                            roles);
                }
        ).toList();
    }

}
