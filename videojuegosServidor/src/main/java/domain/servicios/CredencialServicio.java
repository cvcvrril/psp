package domain.servicios;

import dao.DaoCredencial;
import dao.modelo.Credencial;
import domain.excepciones.BadArgumentException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class CredencialServicio {

    private final DaoCredencial daoCredencial;
    private final Pbkdf2PasswordHash passwordHash;

    @Inject
    public CredencialServicio(DaoCredencial daoCredencial, Pbkdf2PasswordHash passwordHash) {
        this.daoCredencial = daoCredencial;
        this.passwordHash = passwordHash;
    }

    public boolean doLogin(Credencial credencial) {
        Credencial credencialFromDb = daoCredencial.userLogged(credencial).get();
        checkAut(credencial);
        if (credencialFromDb != null) {
            return passwordHash.verify(credencial.getPassword().toCharArray(), credencialFromDb.getPassword());
        }
        return false;
    }

    public void checkAut(Credencial credencial){
        if (!credencial.isAutentificado()){
            throw new BadArgumentException("Usuario no activado");
        }
    }

    public boolean doRegister (Credencial nuevoCredential){
        nuevoCredential.setPassword(passwordHash.generate(nuevoCredential.getPassword().toCharArray()));
        return daoCredencial.addCred(nuevoCredential).get();
    }

}
