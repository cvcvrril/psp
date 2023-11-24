package cliente.ui.pantallas.personaje;

import cliente.domain.errores.ErrorC;
import cliente.domain.usecases.GetAllPersonajesUseCase;
import domain.errores.ApiError;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ListPersonajeViewModel {

    private final GetAllPersonajesUseCase getAllPersonajesUseCase;
    private final ObjectProperty<ListPersonajeState> _state;

    @Inject
    public ListPersonajeViewModel(GetAllPersonajesUseCase getAllPersonajesUseCase) {
        this.getAllPersonajesUseCase = getAllPersonajesUseCase;
        ListPersonajeState ls = null;
        _state = new SimpleObjectProperty<>(new ListPersonajeState(null, null));

    }

    public void loadPersonajes() {
        getAllPersonajesUseCase.getAllPersonajes()
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new ListPersonajeState(null, new ApiError("Error al obtener los personajes", LocalDateTime.now())));
                    } else {
                        _state.setValue(new ListPersonajeState(res.get(), null));
                    }
                });
    }

    public ReadOnlyObjectProperty<ListPersonajeState> getState() {
        return _state;
    }

}
