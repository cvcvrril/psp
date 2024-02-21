package com.example.seguridad.domain.servicios;



import com.example.seguridad.data.modelo.UserEntity;
import com.example.seguridad.data.modelo.error.ErrorObjectSeguridad;
import com.example.seguridad.data.repositorios.UserRepository;
import com.example.seguridad.domain.modelo.Rol;
import com.example.seguridad.domain.modelo.User;
import com.example.seguridad.domain.modelo.UserDTO;
import io.vavr.control.Either;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
                    Rol rol = new Rol(userEntity.getRoles().getId(), userEntity.getRoles().getRol());
                    return new User((long) Math.toIntExact(userEntity.getId()),
                            userEntity.getUsername(),
                            userEntity.getPassword(),
                            rol);
                }
        ).toList();
    }

    public Either<ErrorObjectSeguridad,UserDTO> add(User user){
        Either<ErrorObjectSeguridad, UserDTO> res;
        try {
            String passwordHashed = hashPassword(user.password());
            UserEntity newUser = new UserEntity(0L, user.username(), passwordHashed, null);
            repository.save(newUser);
            UserDTO newUserDTO = new UserDTO(newUser.getId(), newUser.getUsername());
            res = Either.right(newUserDTO);
        }catch (Exception e){
            res = Either.left(new ErrorObjectSeguridad("Hubo un error", LocalDateTime.now()));
        }
        return res;
    }

    private String hashPassword(String password){
        return passwordHash.encode(password);
    }
}
