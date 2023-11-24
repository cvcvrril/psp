package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import domain.errores.ApiError;
import domain.modelo.Personaje;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;

public class ListPersonajeViewModel {

    private final GetAllPersonajesUseCase getAllPersonajesUseCase;

    @Inject
    public ListPersonajeViewModel(GetAllPersonajesUseCase getAllPersonajesUseCase) {
        this.getAllPersonajesUseCase = getAllPersonajesUseCase;
    }


    public void loadPersonajes(){
        List<Personaje> personajes = new ArrayList<>();
        getAllPersonajesUseCase.getAllPersonajes()
                .subscribe(resultado ->
                resultado.get()
                        );

    }

}
