package org.example.springjavafx.servicios;

import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.data.CositasRepository;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.data.modelo.ErrorObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
public class ServiciosCositas {

    private final CositasRepository repository;
    private final ServiciosClaves claves;

    public ServiciosCositas(CositasRepository repository, ServiciosClaves claves) {
        this.repository = repository;
        this.claves = claves;
    }

    public Either<ErrorObject, List<Cosita>> getAllByProgramId(UUID programId){
        Either<ErrorObject, List<Cosita>> res;
        List<Cosita> cositas;
        try {
            cositas = repository.findByCosaId(programId);
            res = Either.right(cositas);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorObject(e.getMessage(), LocalDateTime.now()));
        }
        return res;
    }

    public Either<ErrorObject, Integer> add(Cosita nuevoPermiso){


        return null;
    }
}
