package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.RolEntity;
import com.example.graphql.data.modelo.UserEntity;
import com.example.graphql.data.repositorios.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.graphql.domain.modelo.Rol;
import com.example.graphql.domain.modelo.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServicio {

    private final UserRepository repository;
    private final PasswordEncoder passwordHash;

    public UserServicio(UserRepository repository, PasswordEncoder passwordHash) {
        this.repository = repository;
        this.passwordHash = passwordHash;
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

    public void add(User user){
        Set<RolEntity> rolesEntity = new HashSet<>();
        for (Rol rol: user.roles()){
            rolesEntity.add(new RolEntity(rol.id(), rol.rol()));
        }
        String passwordHashed = hashPassword(user.password());
        UserEntity newUser = new UserEntity(user.id(), user.username(), passwordHashed, rolesEntity);
        repository.save(newUser);
    }

    private String hashPassword(String password){
        return passwordHash.encode(password);
    }

}
