package cliente.ui.pantallas.personaje;

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

    private final UpdatePersonajeUseCase updatePersonajeUseCase;
    private ObjectProperty<UpdatePersonajeState> _state;

    @Inject
    public UpdatePersonajeViewModel(UpdatePersonajeUseCase updatePersonajeUseCase) {
        this.updatePersonajeUseCase = updatePersonajeUseCase;
        _state = new SimpleObjectProperty<>(new UpdatePersonajeState(null));
    }

    public void updatePersonaje(Personaje personaje) {
        updatePersonajeUseCase.updatePersonaje(personaje.getId(), personaje)
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new UpdatePersonajeState(new ApiError("Error al actualizar el personaje", LocalDateTime.now())));
                    } else {
                        _state.setValue(null);
                    }
                }, error -> log.error("Error al actualizar el personaje", error));
    }

    public ReadOnlyObjectProperty<UpdatePersonajeState> getState() {
        return _state;
    }


}
