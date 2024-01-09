package dao.modelo;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"uuid", "user", "password", "email", "autentificado"})
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
        this.rol = "User";
        this.codAut = codAut;
    }
}
