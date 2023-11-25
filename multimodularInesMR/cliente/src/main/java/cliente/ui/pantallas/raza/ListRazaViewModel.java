package cliente.ui.pantallas.raza;

import cliente.domain.usecases.GetAllRazasUseCase;
import cliente.ui.pantallas.personaje.ListPersonajeState;
import domain.errores.ApiError;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class ListRazaViewModel {

    private final GetAllRazasUseCase getAllRazasUseCase;
    private final ObjectProperty<ListRazaState> _state;

    public ListRazaViewModel(GetAllRazasUseCase getAllRazasUseCase) {
        this.getAllRazasUseCase = getAllRazasUseCase;
        _state = new SimpleObjectProperty<>(new ListRazaState(null, null));
    }

    public void loadRazas() {
        try {
            getAllRazasUseCase.getAllRazas()
                    .subscribe(res -> {
                                if (res.isLeft()) {
                                    _state.setValue(new ListRazaState(null, new ApiError("Error al obtener los personajes", LocalDateTime.now())));
                                } else {
                                    _state.setValue(new ListRazaState(res.get(), null));
                                }
                            },
                            error -> log.error("Error al obtener los personajes", error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public ReadOnlyObjectProperty<ListRazaState> getState() {
        return _state;
    }
}
