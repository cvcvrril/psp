package cliente.ui.pantallas.raza;

import domain.errores.ApiError;
import domain.modelo.Raza;
import lombok.Data;

import java.util.List;

@Data
public class ListRazaState {
    private final List<Raza> razas;
    private final ApiError apiError;

}
