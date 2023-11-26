package servidor.domain.servicios;


import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.extern.log4j.Log4j2;
import servidor.dao.DaoRegistro;

@Log4j2
public class ServicioRegistro {

    private final DaoRegistro daoRegistro;
    private Pbkdf2PasswordHash passwordHash;

    @Inject
    public ServicioRegistro(DaoRegistro daoRegistro, Pbkdf2PasswordHash passwordHash) {
        this.daoRegistro = daoRegistro;
        this.passwordHash = passwordHash;
    }

    public Either<ApiError, Usuario>get(int id){
        return daoRegistro.get(id);
    }

    public Either<ApiError, Integer> add(Usuario nuevoUsuario){
        nuevoUsuario.setPassword(hashPass(nuevoUsuario.getPassword()));
        return daoRegistro.add(nuevoUsuario);
    }

    public String hashPass(String pass){
        return passwordHash.generate(pass.toCharArray());
    }

}
