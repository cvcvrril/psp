package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.CosasRepository;
import org.example.springjavafx.data.CositasRepository;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ServiciosCosas {

    private final CosasRepository repository;
    private final CositasRepository repositoryPermisos;
    private final ServiciosClaves claves;

    public ServiciosCosas(CosasRepository repository, CositasRepository repositoryPermisos, ServiciosClaves claves) {
        this.repository = repository;
        this.repositoryPermisos = repositoryPermisos;
        this.claves = claves;
    }

    public Either<ErrorObject, List<Cosa>> getALl(){
        Either<ErrorObject, List<Cosa>> res;
        List<Cosa> cosas;
        try {
            cosas = repository.findAll();
            res = Either.right(cosas);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, List<Cosa>> getAllById(UUID idUser){
        Either<ErrorObject, List<Cosa>> res;
        List<Cosa> cosas;
        try {
            cosas = repository.findByUserId(idUser);
            res = Either.right(cosas);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> add(Cosa nuevaCosa, Cosita nuevoPermiso){
        Either<ErrorObject, Integer> res;
        try {
            String contrasenaEncriptada = claves.encryptCode(nuevaCosa.getContrasena());
            //String firmaPrograma = claves.signCode();
            nuevaCosa.setContrasena(contrasenaEncriptada);
            repository.save(nuevaCosa);
            repositoryPermisos.save(nuevoPermiso);
            res = Either.right(1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> changePassword(Cosa programaContrasenaCambiada){
        Either<ErrorObject, Integer> res;
        try {
            String nuevaContrasenaEncriptada = claves.encryptCode(programaContrasenaCambiada.getContrasena());
            programaContrasenaCambiada.setContrasena(nuevaContrasenaEncriptada);
            repository.save(programaContrasenaCambiada);
            res = Either.right(1);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

}
