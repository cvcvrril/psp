package servidor.domain.servicios;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import lombok.extern.log4j.Log4j2;
import servidor.dao.DaoLogin;
import servidor.domain.ConstantesDomain;

import java.time.LocalDateTime;

@Log4j2
public class ServicioLogin {

    private final DaoLogin daoLogin;
    private final Pbkdf2PasswordHash hasheoPass;

    @Inject
    public ServicioLogin(DaoLogin daoLogin, Pbkdf2PasswordHash hasheoPass) {
        this.daoLogin = daoLogin;
        this.hasheoPass = hasheoPass;
    }

    public Either<ApiError, Usuario> check(String usuario){
        return daoLogin.check(usuario);
    }

    public Either<ApiError, Usuario> login(String usuario, String pass){
        Either<ApiError, Usuario> res;
        Usuario user = check(usuario).get();
        try {
            if(hasheoPass.verify(pass.toCharArray(), user.getPassword())){
                res = Either.right(user);
            } else {
                res = Either.left(new ApiError(ConstantesDomain.CONTRASENA_INCORRECTA, LocalDateTime.now()));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
            res = Either.left(new ApiError(ConstantesDomain.ERROR_AL_INICIAR_SESION, LocalDateTime.now()));
        }
        return res;
    }
}
