package org.example.springjavafx.servicios;

import org.example.springjavafx.data.UserRepository;
import org.example.springjavafx.data.modelo.Cache;
import org.example.springjavafx.data.modelo.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public User getByPassword(String passwordUser){
        return repository.findByPassword(passwordUser).get();
    }

    public String hashPassword(String userPassword){
         return passwordHash.encode(userPassword);
    }

    public boolean doLogin(User userLogin){
        User userFromRepository = repository.findByName(userLogin.getName()).get();
        if (userFromRepository != null){
            if (passwordHash.matches(userLogin.getPassword(), userFromRepository.getPassword())){
                passwordCache.setUserPassword(userLogin.getPassword());
                return true;
            }
            else {
                return false;
            }
        } else {
            return false;
        }
    }

}
