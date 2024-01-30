package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.PermisoRepository;
import org.example.springjavafx.data.modelo.Permiso;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ServiciosPermisos {

    private final PermisoRepository repository;
    private final ServiciosClaves claves;

    public ServiciosPermisos(PermisoRepository repository, ServiciosClaves claves) {
        this.repository = repository;
        this.claves = claves;
    }

    public Either<ErrorObject, List<Permiso>> getAllByProgramId(UUID programId){
        Either<ErrorObject, List<Permiso>> res;
        List<Permiso> permisos;
        try {
            permisos = repository.findByProgramaId(programId);
            res = Either.right(permisos);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> add(Permiso nuevoPermiso, String contrasena){
        Either<ErrorObject, Integer> res;
        try {
            String firmaEncriptada = claves.encryptCode(contrasena);
            nuevoPermiso.setAsym(firmaEncriptada);
            repository.save(nuevoPermiso);
            res = Either.right(1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }
}
