package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.DeletePersonajeUseCase;
import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.ui.ConstantesUi;
import domain.errores.ApiError;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class DeletePersonajeViewModel {

    private final GetAllPersonajesUseCase getAllPersonajesUseCase;
    private final DeletePersonajeUseCase deletePersonajeUseCase;
    private ObjectProperty<DeletePersonajeState> _state;

    @Inject
    public DeletePersonajeViewModel(GetAllPersonajesUseCase getAllPersonajesUseCase, DeletePersonajeUseCase deletePersonajeUseCase) {
        this.getAllPersonajesUseCase = getAllPersonajesUseCase;
        this.deletePersonajeUseCase = deletePersonajeUseCase;
        _state = new SimpleObjectProperty<>(new DeletePersonajeState(null, null, null));
    }

    public void loadAllPersonajes() {
        try {
            getAllPersonajesUseCase.getAllPersonajes()
                    .subscribe(res -> {
                                if (res.isLeft()) {
                                    _state.setValue(new DeletePersonajeState(null, new ApiError(ConstantesUi.ERROR_AL_OBTENER_LOS_PERSONAJES, LocalDateTime.now()), null));
                                } else {
                                    _state.setValue(new DeletePersonajeState(null, null, res.get()));
                                }
                            },
                            error -> log.error(ConstantesUi.ERROR_AL_OBTENER_LOS_PERSONAJES, error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deletePersonaje(int id) {
        deletePersonajeUseCase.deletePersonaje(id)
                .subscribe(res -> {
                            if (res.isLeft()) {
                                _state.setValue(new DeletePersonajeState(null, new ApiError(ConstantesUi.ERROR_AL_ELIMINAR_EL_PERSONAJE, LocalDateTime.now()), null));
                            } else {
                                loadAllPersonajes();
                            }
                        }, error -> log.error(ConstantesUi.ERROR_AL_ELIMINAR_EL_PERSONAJE, error));

    }

    public ReadOnlyObjectProperty<DeletePersonajeState> getState() {
        return _state;
    }

}
