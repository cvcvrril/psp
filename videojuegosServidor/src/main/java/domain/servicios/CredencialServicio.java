package domain.servicios;

import dao.DaoCredencial;
import dao.modelo.Credencial;
import domain.excepciones.BadArgumentException;
import domain.excepciones.WrongObjectException;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.utils.RandomBytesGenerator;
import lombok.extern.log4j.Log4j2;
import jakarta.validation.Validator;

@Log4j2
public class CredencialServicio {

    private final DaoCredencial daoCredencial;
    private final Pbkdf2PasswordHash passwordHash;
    private final MandarMail m;

    @Inject
    public CredencialServicio(DaoCredencial daoCredencial, Pbkdf2PasswordHash passwordHash, Validator validator, MandarMail m) {
        this.daoCredencial = daoCredencial;
        this.passwordHash = passwordHash;
        this.m = m;
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

    public boolean actualizarPassword(Credencial actualizadoCredencial){
        if (daoCredencial.actualizarPassword(actualizadoCredencial).get()){
            actualizadoCredencial.setPassword(passwordHash.generate(actualizadoCredencial.getPassword().toCharArray()));
        } else {
            throw new BadArgumentException("Ha habido un error al actualizar la contraseña");
        }
        return false;
    }

    public Credencial getCredencialUser(String username){
        return daoCredencial.getCredencialUser(username).get();
    }

    public Credencial getCredencialEmail(String email){
        return daoCredencial.getCredencialEmail(email).get();
    }

    public Credencial getCredencialCode(String code){
        return daoCredencial.getCredencialCode(code).get();
    }

    public void mandarMail(String email) {
        try {
            RandomBytesGenerator randomBytesGenerator = new RandomBytesGenerator();
            String random = randomBytesGenerator.randomBytes();
            Credencial credencialMandarMail = getCredencialEmail(email);
            credencialMandarMail.setCodAut(random);
            m.generateAndSendEmail(email, "<html><body><a href=\"http://localhost:8080/videojuegosServidor-1.0-SNAPSHOT/Activation?codigo="+ random+"\">Activación</a></body></html>", "Prueba activacion");
        }catch (MessagingException e) {
            throw new BadArgumentException("Ha habido un error al mandar el mail");
        }
    }

    public void mandarMailCambioPassword(String email){
        try {
            RandomBytesGenerator randomBytesGenerator = new RandomBytesGenerator();
            String random = randomBytesGenerator.randomBytes();
            Credencial credencialMandarMail = getCredencialEmail(email);
            if (credencialMandarMail != null){
                credencialMandarMail.setCodAut(random);
                m.generateAndSendEmail(email, "<html><body><a href=\"http://localhost:8080/videojuegosServidor-1.0-SNAPSHOT/ChangePassword?codigo="+ random+"\">Cambio contraseña</a></body></html>", "Cambiar contraseña");
            } else {
                throw new WrongObjectException("No se encuentra ninguna credencial con ese correo. ¿Desea mejor registrarse?");
            }
        }catch (MessagingException e) {
            throw new BadArgumentException("Ha habido un error al mandar el mail");
        }
    }

}
