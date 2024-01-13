package dao.modelo;

import dao.ConstantsDao;
import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({ConstantsDao.UUID, ConstantsDao.USER, ConstantsDao.PASSWORD, ConstantsDao.EMAIL, ConstantsDao.AUTENTIFICADO})
public class Credencial {

    private UUID uuid;
    private String user;
    private String password;
    private String email;
    private boolean autentificado;
    private String rol;
    private String codAut;


    public Credencial(String user, String password, String email, boolean autentificado, String rol, String codAut) {
        uuid = java.util.UUID.randomUUID();
        this.user = user;
        this.password = password;
        this.email = email;
        this.autentificado = autentificado;
        this.rol = rol;
        this.codAut = codAut;
    }
}
