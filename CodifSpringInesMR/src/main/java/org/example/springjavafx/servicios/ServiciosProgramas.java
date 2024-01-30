package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.ProgramaRepository;
import org.example.springjavafx.data.PermisoRepository;
import org.example.springjavafx.data.modelo.Programa;
import org.example.springjavafx.data.modelo.Permiso;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ServiciosProgramas {

    private final ProgramaRepository repository;
    private final PermisoRepository repositoryPermisos;
    private final ServiciosClaves claves;

    public ServiciosProgramas(ProgramaRepository repository, PermisoRepository repositoryPermisos, ServiciosClaves claves) {
        this.repository = repository;
        this.repositoryPermisos = repositoryPermisos;
        this.claves = claves;
    }

    public Either<ErrorObject, List<Programa>> getALl() {
        Either<ErrorObject, List<Programa>> res;
        List<Programa> programas;
        try {
            programas = repository.findAll();
            res = Either.right(programas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, List<Programa>> getAllById(UUID idUser) {
        Either<ErrorObject, List<Programa>> res;
        List<Programa> programas;
        try {
            programas = repository.findByUserId(idUser);
            res = Either.right(programas);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> add(Programa nuevoPrograma, Permiso nuevoPermiso) {
        Either<ErrorObject, Integer> res;
        try {
            String contrasenaEncriptada = claves.encryptCode(nuevoPrograma.getContrasena());
            String firmaNuevoPrograma = claves.signCode(nuevoPrograma.getContrasena(), nuevoPrograma.getUsername()).get();
            nuevoPrograma.setFirma(firmaNuevoPrograma);
            nuevoPrograma.setContrasena(contrasenaEncriptada);
            repository.save(nuevoPrograma);
            String firmaEncriptada = claves.encryptCode(contrasenaEncriptada);
            nuevoPermiso.setAsym(firmaEncriptada);
            repositoryPermisos.save(nuevoPermiso);
            res = Either.right(1);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> changePassword(Programa programaContrasenaCambiada) {
        Either<ErrorObject, Integer> res;
        try {
            String nuevaContrasenaEncriptada = claves.encryptCode(programaContrasenaCambiada.getContrasena());
            String firmaNuevoPrograma = claves.signCode(programaContrasenaCambiada.getContrasena(), programaContrasenaCambiada.getUsername()).get();
            programaContrasenaCambiada.setFirma(firmaNuevoPrograma);
            programaContrasenaCambiada.setContrasena(nuevaContrasenaEncriptada);
            repository.save(programaContrasenaCambiada);
            res = Either.right(1);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

}
