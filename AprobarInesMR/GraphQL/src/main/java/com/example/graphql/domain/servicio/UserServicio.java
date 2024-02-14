package com.example.graphql.domain.servicio;


import com.example.graphql.data.modelo.UserEntity;
import com.example.graphql.data.modelo.error.ErrorObject;
import com.example.graphql.data.repositorios.UserRepository;
import com.example.graphql.domain.modelo.Rol;
import com.example.graphql.domain.modelo.User;
import io.vavr.control.Either;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Either<ErrorObject,User> add(User user){
        Either<ErrorObject,User> res;
        try {
            //String passwordHashed = hashPassword(user.password());
            //String passwordHashed = user.password();
            UserEntity newUser = new UserEntity(0L, user.username(), user.password(), null);
            repository.save(newUser);
            res = Either.right(user);
        }catch (Exception e){
            res = Either.left(new ErrorObject("Hubo un error", LocalDateTime.now()));
        }
        return res;
    }

    public User login(User loginUser) {
        return null;
    }
}