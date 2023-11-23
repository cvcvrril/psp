package cliente.ui.pantallas.personaje;

import domain.errores.ApiError;
import domain.modelo.Personaje;
import lombok.Data;

import java.util.List;

@Data
public class ListPersonajeState {
    private final List<Personaje> personajes;
    private final ApiError apiError;


}
