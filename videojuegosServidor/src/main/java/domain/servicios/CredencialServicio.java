package domain.servicios;

import dao.interfaces.DaoCredencial;
import dao.modelo.Credencial;
import domain.ConstantsDomain;
import domain.excepciones.BadArgumentException;
import domain.excepciones.WrongObjectException;
import jakarta.inject.Inject;
import jakarta.mail.MessagingException;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.utils.RandomBytesGenerator;
import lombok.extern.log4j.Log4j2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class CredencialServicio {


    private final DaoCredencial daoCredencial;
    private final Pbkdf2PasswordHash passwordHash;
    private final MandarMail m;
    private final RandomBytesGenerator randomBytesGenerator;

    @Inject
    public CredencialServicio(DaoCredencial daoCredencial, Pbkdf2PasswordHash passwordHash, MandarMail m, RandomBytesGenerator randomBytesGenerator) {
        this.daoCredencial = daoCredencial;
        this.passwordHash = passwordHash;
        this.m = m;
        this.randomBytesGenerator = randomBytesGenerator;
    }

    public boolean doLogin(Credencial credencial) {
        Credencial credencialFromDb = daoCredencial.userLogged(credencial).get();
        checkAut(credencial);
        if (credencialFromDb != null) {
            return passwordHash.verify(credencial.getPassword().toCharArray(), credencialFromDb.getPassword());
        }
        return false;
    }

    private void checkAut(Credencial credencial){
        if (!credencial.isAutentificado()){
            throw new BadArgumentException(ConstantsDomain.USUARIO_NO_ACTIVADO);
        }
    }

    private void checkEmailRegex(String email){
        String emailRegex = ConstantsDomain.EMAIL_REGEX;
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()){
            throw new BadArgumentException(ConstantsDomain.EL_EMAIL_INTRODUCIDO_NO_ES_VALIDO);
        }
    }

    public boolean doRegister (Credencial nuevoCredential){
        if (nuevoCredential.getEmail()!= null){
            checkEmailRegex(nuevoCredential.getEmail());
            nuevoCredential.setPassword(passwordHash.generate(nuevoCredential.getPassword().toCharArray()));
            return daoCredencial.addCred(nuevoCredential).get();
        }else {
            throw new BadArgumentException(ConstantsDomain.HA_HABIDO_UN_ERROR_CON_EL_REGISTRO);
        }
    }

    public boolean actualizarPassword(Credencial actualizadoCredencial){
        if (daoCredencial.actualizarPassword(actualizadoCredencial).get()){
            actualizadoCredencial.setPassword(passwordHash.generate(actualizadoCredencial.getPassword().toCharArray()));
        } else {
            throw new BadArgumentException(ConstantsDomain.HA_HABIDO_UN_ERROR_AL_ACTUALIZAR_LA_CONTRASENA);
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
            String random = randomBytesGenerator.randomBytes();
            Credencial credencialMandarMail = getCredencialEmail(email);
            credencialMandarMail.setCodAut(random);
            m.generateAndSendEmail(email, ConstantsDomain.URL_ACTIVACION_1 + random + ConstantsDomain.URL_ACTIVACION_2, ConstantsDomain.ACTIVACION_DE_LA_CUENTA);
        }catch (MessagingException e) {
            throw new BadArgumentException(ConstantsDomain.HA_HABIDO_UN_ERROR_AL_MANDAR_EL_MAIL);
        }
    }

    public void mandarMailCambioPassword(String email){
        try {
            String random = randomBytesGenerator.randomBytes();
            Credencial credencialMandarMail = getCredencialEmail(email);
            if (credencialMandarMail != null){
                credencialMandarMail.setCodAut(random);
                m.generateAndSendEmail(email, ConstantsDomain.URL_CONTRASENA_1 + random + ConstantsDomain.URL_CONTRASENA_2, ConstantsDomain.CAMBIO_DE_CONTRASENA);
            } else {
                throw new WrongObjectException(ConstantsDomain.NO_SE_ENCUENTRA_NINGUNA_CREDENCIAL_CON_ESE_CORREO_DESEA_MEJOR_REGISTRARSE);
            }
        }catch (MessagingException e) {
            throw new BadArgumentException(ConstantsDomain.HA_HABIDO_UN_ERROR_AL_MANDAR_EL_MAIL);
        }
    }

}
