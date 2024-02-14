package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.UserEntity;
import com.example.graphql.data.repositorios.UserRepository;
import com.example.graphql.domain.modelo.Rol;
import com.example.graphql.domain.modelo.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class UserServicio {

    private final UserRepository repository;
    //private final PasswordEncoder passwordHash;

    public UserServicio(UserRepository repository) {
        this.repository = repository;
        //this.passwordHash = passwordHash;
    }

    public List<User> getAll(){
        return repository.getAllWithPermisos().stream().map(
                userEntity -> {
                    List<Rol> roles = userEntity.getRoles().stream().map(
                        rolEntity ->
                                new Rol((long) Math.toIntExact(rolEntity.getId()),
                                        rolEntity.getRol()
                                )
                    ).toList();
                    return new User((long) Math.toIntExact(userEntity.getId()),
                            userEntity.getUsername(),
                            userEntity.getPassword(),
                            roles);
                }
        ).toList();
    }

    public User add(User user){
        //String passwordHashed = hashPassword(user.password());
        String passwordHashed = user.password();
        UserEntity newUser = new UserEntity(0L, user.username(), user.password(), null);
        repository.save(newUser);
        return user;
    }

    private String hashPassword(String password){
        //return passwordHash.encode(password);
        return null;
    }

}
