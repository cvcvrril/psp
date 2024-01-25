package org.example.springjavafx.servicios;

import org.example.springjavafx.data.UserRepository;
import org.example.springjavafx.data.modelo.User;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ServiciosUsuarios {

    private final UserRepository repository;
    //private final Pbkdf2PasswordEncoder passwordHash;

    //TODO: ENCONTRAR LA PUÑETERA MANERA DE QUE NO ME VUELVA A SALIR LO DEL PUTO BEANS TT.TT

    public ServiciosUsuarios(UserRepository repository) {
        this.repository = repository;
        //this.passwordHash = passwordHash;
    }

    public void addUser(User nuevoUsuario){
        repository.save(nuevoUsuario);
    }

    public void hashPassword(String userPassword){


    }

    public void checkPassword(String passwordIntroducida){
        //TODO: CAMBIAR A BOOLEANO EL MÉTODO (EN VEZ DE VOID)
        //TODO: MONTAR UN MÉTODO PARA CHECKEAR LA CONTRASEÑA QUE HAY EN LA BASE DE DATOS CON LA QUE INTRODUCE EL USUARIO
        //TODO: LUEGO, PASAR LA CONTRASEÑA AL SINGLETON CACHE PARA QUE SE LA GUARDE DURANTE TODO EL PROCESO EN LA MEMORIA
    }

}
