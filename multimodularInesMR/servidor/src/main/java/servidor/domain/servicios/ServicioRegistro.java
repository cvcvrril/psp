package servidor.domain.servicios;


import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.log4j.Log4j2;
import servidor.dao.DaoRegistro;
import servidor.domain.ConstantesDomain;
import servidor.domain.modelo.excepciones.BadArgumentException;

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
        validPass(nuevoUsuario.getPassword());
        validUser(nuevoUsuario.getUsername());
        nuevoUsuario.setPassword(hashPass(nuevoUsuario.getPassword()));
        return daoRegistro.add(nuevoUsuario);
    }

    private void validPass(String pass){
        if (pass.length() < 4){
            throw new BadArgumentException(ConstantesDomain.LA_CONTRASENA_DEBE_DE_CONTENER_MAS_DE_4_CARACTERES);
        }
    }

    private void validUser(String user){
        if (!Character.isUpperCase(user.charAt(0))){
            throw new BadArgumentException(ConstantesDomain.EL_USUARIO_DEBE_DE_EMPEZAR_POR_MAYUSCULA);
        }
    }

    public String hashPass(String pass){
        return passwordHash.generate(pass.toCharArray());
    }

}
