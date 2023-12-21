package dao.modelo;

import jakarta.json.bind.annotation.JsonbPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonbPropertyOrder({"id", "titulo", "descripcion"})
public class Credencial {

    private int id;
    private String user;
    private String password;
    private String email;
    private boolean autentificado;
    //private LocalDateTime registro;

}
