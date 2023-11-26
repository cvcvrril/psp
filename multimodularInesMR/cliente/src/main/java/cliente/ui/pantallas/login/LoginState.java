package cliente.ui.pantallas.login;

import domain.errores.ApiError;
import domain.modelo.Usuario;
import lombok.Data;

@Data
public class LoginState {
    private final Usuario usuario;
    private final ApiError error;

}
