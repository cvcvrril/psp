package dao.imp;

import dao.ConstantsDAO;
import dao.DaoCredencial;
import dao.data.StaticLists;
import dao.modelo.Credencial;
import domain.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;
import java.util.Optional;

@Log4j2
public class DaoCredencialImp implements DaoCredencial {

    @Override
    public Either<ApiError, Credencial> userLogged(Credencial credencial) {
        Either<ApiError, Credencial> res;
        try {
            Credencial c = StaticLists.listaCredenciales.stream().filter(credencial1 -> credencial1.getUser().equals(credencial.getUser())).findFirst().orElse(null);
            res = Either.right(c);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    @Override
    public Either<ApiError, Boolean> addCred(Credencial nuevoCredential){
        Either<ApiError, Boolean> res;
        try {
            res = Either.right(StaticLists.listaCredenciales.add(nuevoCredential));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;

    }

    @Override
    public Either<ApiError, Boolean> actualizarPassword(Credencial actualizadoCredencial) {
        try {
            Optional<Credencial> credencialOptional = StaticLists.listaCredenciales.stream()
                    .filter(credencial -> credencial.getUser().equals(actualizadoCredencial.getUser()))
                    .findFirst();

            if (credencialOptional.isPresent()) {
                Credencial credencialExistente = credencialOptional.get();
                credencialExistente.setPassword(actualizadoCredencial.getPassword());
                return Either.right(true);
            } else {
                ApiError error = new ApiError("Usuario no encontrado.No se encontró el usuario para actualizar la contraseña.", LocalDateTime.now());
                return Either.left(error);
            }
        } catch (Exception e) {
            ApiError error = new ApiError("Error en la actualización. Ocurrió un error al actualizar la contraseña.", LocalDateTime.now());
            return Either.left(error);
        }
    }

    @Override
    public Either<ApiError, Credencial> getCredencialUser(String username) {
        for (Credencial credencial : StaticLists.listaCredenciales) {
            if (credencial.getUser().equals(username)) {
                return Either.right(credencial);
            }
        }
        return Either.left(new ApiError("Credencial no encontrada para el usuario.", LocalDateTime.now()));
    }

    @Override
    public Either<ApiError, Credencial> getCredencialEmail(String email) {
        for (Credencial credencial : StaticLists.listaCredenciales) {
            if (credencial.getEmail().equals(email)) {
                return Either.right(credencial);
            }
        }
        return Either.left(new ApiError("Credencial no encontrada para el usuario.", LocalDateTime.now()));
    }

    @Override
    public Either<ApiError, Credencial> getCredencialCode(String code) {
        for (Credencial credencial : StaticLists.listaCredenciales) {
            if (credencial.getCodAut().equals(code)) {
                return Either.right(credencial);
            }
        }
        return Either.left(new ApiError("Credencial no encontrada para el usuario.", LocalDateTime.now()));
    }


}
