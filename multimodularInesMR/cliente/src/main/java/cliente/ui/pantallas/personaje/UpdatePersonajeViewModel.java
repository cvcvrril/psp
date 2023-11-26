package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.domain.usecases.GetPersonajeByIdUseCase;
import cliente.domain.usecases.UpdatePersonajeUseCase;
import cliente.ui.ConstantesUi;
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
                                    _state.setValue(new UpdatePersonajeState(null, new ApiError(ConstantesUi.ERROR_AL_OBTENER_LOS_PERSONAJES, LocalDateTime.now()), null));
                                } else {
                                    _state.setValue(new UpdatePersonajeState(null, null, res.get()));
                                }
                            },
                            error -> log.error(ConstantesUi.ERROR_AL_OBTENER_LOS_PERSONAJES, error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public void updatePersonaje(Personaje personaje) {
        updatePersonajeUseCase.updatePersonaje(personaje)
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new UpdatePersonajeState(null, new ApiError(ConstantesUi.ERROR_AL_ACTUALIZAR_EL_PERSONAJE, LocalDateTime.now()), null));
                    } else {
                        _state.setValue(new UpdatePersonajeState(null, null, null));
                    }
                }, error -> log.error(ConstantesUi.ERROR_AL_ACTUALIZAR_EL_PERSONAJE, error));
    }

    public ReadOnlyObjectProperty<UpdatePersonajeState> getState() {
        return _state;
    }


}
