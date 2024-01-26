package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.UserRepository;
import org.example.springjavafx.data.modelo.Cache;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.example.springjavafx.data.modelo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
@Service
public class ServiciosUsuarios {

    private final UserRepository repository;
    private final PasswordEncoder passwordHash;
    private final Cache passwordCache;


    public ServiciosUsuarios(UserRepository repository, PasswordEncoder passwordHash, Cache passwordCache) {
        this.repository = repository;
        this.passwordHash = passwordHash;
        this.passwordCache = passwordCache;
    }

    public void addUser(User nuevoUsuario){
        String passwordHashed = hashPassword(nuevoUsuario.getPassword());
        nuevoUsuario.setPassword(passwordHashed);
        repository.save(nuevoUsuario);
    }

    public String hashPassword(String userPassword){
         return passwordHash.encode(userPassword);
    }

    public Either<ErrorObject,User> doLogin(User userLogin){
        Either<ErrorObject, User> res;
        try {
            User userFromRepository = repository.findByName(userLogin.getName()).get();
            if (userFromRepository != null){
                if (passwordHash.matches(userLogin.getPassword(), userFromRepository.getPassword())){
                    passwordCache.setUserPassword(userLogin.getPassword());
                    res = Either.right(userFromRepository);
                }
                else {
                    res = Either.left(new ErrorObject("Erro con la contraseña", LocalDateTime.now()));
                }
            } else {
                res = Either.left(new ErrorObject("Error al encontrar el usuario", LocalDateTime.now()));
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
        return res;
    }
}
