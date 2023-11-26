package cliente.ui.pantallas.faccion;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import lombok.Data;

import java.util.List;

@Data
public class DeletePersonajePorFaccionState {

    private final Personaje personaje;
    private final ApiError apiError;
    private final List<Faccion> facciones;

}
