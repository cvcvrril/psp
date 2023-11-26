package cliente.ui.pantallas.personaje;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import lombok.Data;

import java.util.List;

@Data
public class DeletePersonajeState {

    private final Personaje personaje;
    private final ApiError error;
    private final List<Personaje> personajes;

}
