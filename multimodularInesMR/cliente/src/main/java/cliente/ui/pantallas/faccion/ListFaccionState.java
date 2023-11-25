package cliente.ui.pantallas.faccion;

import domain.errores.ApiError;
import domain.modelo.Faccion;
import lombok.Data;

import java.util.List;

@Data
public class ListFaccionState {

    private final List<Faccion> facciones;
    private final ApiError apiError;

}
