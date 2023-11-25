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
import lombok.extern.log4j.Log4j2;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ListPersonajeViewModel {

    private final GetAllPersonajesUseCase getAllPersonajesUseCase;
    private final ObjectProperty<ListPersonajeState> _state;

    @Inject
    public ListPersonajeViewModel(GetAllPersonajesUseCase getAllPersonajesUseCase) {
        this.getAllPersonajesUseCase = getAllPersonajesUseCase;
        _state = new SimpleObjectProperty<>(new ListPersonajeState(null, null));

    }

    public void loadPersonajes() {
        try {
            getAllPersonajesUseCase.getAllPersonajes()
                    .subscribe(res -> {
                                if (res.isLeft()) {
                                    _state.setValue(new ListPersonajeState(null, new ApiError("Error al obtener los personajes", LocalDateTime.now())));
                                } else {
                                    _state.setValue(new ListPersonajeState(res.get(), null));

                                }
                            },
                            error -> log.error("Error al obtener los personajes", error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public ReadOnlyObjectProperty<ListPersonajeState> getState() {
        return _state;
    }

}
