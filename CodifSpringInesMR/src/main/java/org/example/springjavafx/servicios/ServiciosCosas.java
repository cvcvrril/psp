package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.CosasRepository;
import org.example.springjavafx.data.CositasRepository;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.example.springjavafx.utils.RandomBytesGenerator;
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
    private final RandomBytesGenerator ksa;

    public ServiciosCosas(CosasRepository repository, CositasRepository repositoryPermisos, ServiciosClaves claves, RandomBytesGenerator ksa) {
        this.repository = repository;
        this.repositoryPermisos = repositoryPermisos;
        this.claves = claves;
        this.ksa = ksa;
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

    public Either<ErrorObject, Integer> add(Cosa nuevoPrograma, Cosita nuevoPermiso){
        Either<ErrorObject, Integer> res;
        try {
            String contrasenaEncriptada = claves.encryptCode(nuevoPrograma.getContrasena(), ksa.randomBytes());
            String firmaNuevoPrograma = claves.encryptCode(ksa.randomBytes(), claves.privateKeyUserString(nuevoPrograma.getUsername()));
            //String firmaNuevoPrograma = claves.signCode(ksa.randomBytes(), nuevoPrograma.getUsername()).get();
            nuevoPrograma.setFirma(firmaNuevoPrograma);
            nuevoPrograma.setContrasena(contrasenaEncriptada);
            repository.save(nuevoPrograma);
            String ksaEncriptada = claves.encryptCode(ksa.randomBytes(), claves.publicKeyUserString(nuevoPrograma.getUsername()));
            nuevoPermiso.setAsym(ksaEncriptada);
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
            String nuevaContrasenaEncriptada = claves.encryptCode(programaContrasenaCambiada.getContrasena(), ksa.randomBytes());
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
