package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.AddPersonajeUseCase;
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
public class AddPersonajeViewModel {

    private final AddPersonajeUseCase addPersonajeUseCase;
    private ObjectProperty<AddPersonajeState> _state;

    @Inject
    public AddPersonajeViewModel(AddPersonajeUseCase addPersonajeUseCase) {
        this.addPersonajeUseCase = addPersonajeUseCase;
        _state = new SimpleObjectProperty<>(new AddPersonajeState(null));
    }

    public void addPersonaje(Personaje personaje) {
        addPersonajeUseCase.addPerdonaje(personaje)
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new AddPersonajeState(new ApiError(ConstantesUi.ERROR_AL_ANADIR_EL_PERSONAJE, LocalDateTime.now())));
                    } else {
                        _state.setValue(new AddPersonajeState(null));
                    }
                }, error -> log.error(ConstantesUi.ERROR_AL_ANADIR_EL_PERSONAJE, error));

    }

    public ReadOnlyObjectProperty<AddPersonajeState> getState() {
        return _state;
    }

}
