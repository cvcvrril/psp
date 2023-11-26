package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.domain.usecases.GetPersonajeByIdUseCase;
import cliente.domain.usecases.UpdatePersonajeUseCase;
import domain.errores.ApiError;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class UpdatePersonajeViewModel {

    private final GetPersonajeByIdUseCase getPersonajeByIdUseCase;
    private final GetAllPersonajesUseCase getAllPersonajesUseCase;
    private final UpdatePersonajeUseCase updatePersonajeUseCase;

    private ObjectProperty<UpdatePersonajeState> _state;

    @Inject
    public UpdatePersonajeViewModel(GetPersonajeByIdUseCase getPersonajeByIdUseCase, GetAllPersonajesUseCase getAllPersonajesUseCase, UpdatePersonajeUseCase updatePersonajeUseCase) {
        this.getPersonajeByIdUseCase = getPersonajeByIdUseCase;
        this.getAllPersonajesUseCase = getAllPersonajesUseCase;
        this.updatePersonajeUseCase = updatePersonajeUseCase;
        _state = new SimpleObjectProperty<>(new UpdatePersonajeState(null, null, null));
    }

    public void loadAllPersonajes() {
        try {
            getAllPersonajesUseCase.getAllPersonajes()
                    .subscribe(res -> {
                                if (res.isLeft()) {
                                    _state.setValue(new UpdatePersonajeState(null, new ApiError("Error al obtener los personajes", LocalDateTime.now()), null));
                                } else {
                                    _state.setValue(new UpdatePersonajeState(null, null, res.get()));
                                }
                            },
                            error -> log.error("Error al obtener los personajes", error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public void updatePersonaje(Personaje personaje) {
        updatePersonajeUseCase.updatePersonaje(personaje)
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new UpdatePersonajeState(null, new ApiError("Error al actualizar el personaje", LocalDateTime.now()), null));
                    } else {
                        _state.setValue(new UpdatePersonajeState(null, null, null));
                    }
                }, error -> log.error("Error al actualizar el personaje", error));
    }

    public ReadOnlyObjectProperty<UpdatePersonajeState> getState() {
        return _state;
    }


}
