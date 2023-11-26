package cliente.ui.pantallas.faccion;


import cliente.domain.usecases.DeletePersonajePorFaccionUseCase;
import cliente.domain.usecases.GetAllFaccionesUseCase;
import domain.errores.ApiError;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class DeletePersonajePorFaccionViewModel {

    private final GetAllFaccionesUseCase getAllFaccionesUseCase;
    private final DeletePersonajePorFaccionUseCase deletePersonajePorFaccionUseCase;
    private ObjectProperty<DeletePersonajePorFaccionState> _state;

    @Inject
    public DeletePersonajePorFaccionViewModel(GetAllFaccionesUseCase getAllFaccionesUseCase, DeletePersonajePorFaccionUseCase deletePersonajePorFaccionUseCase) {
        this.getAllFaccionesUseCase = getAllFaccionesUseCase;
        this.deletePersonajePorFaccionUseCase = deletePersonajePorFaccionUseCase;
        _state = new SimpleObjectProperty<>(new DeletePersonajePorFaccionState(null, null, null));
    }

    public void loadAllFacciones(){
        try {
            getAllFaccionesUseCase.getAllFacciones()
                    .subscribe(res -> {
                                if (res.isLeft()) {
                                    _state.setValue(new DeletePersonajePorFaccionState(null, new ApiError("Error al obtener los personajes", LocalDateTime.now()), null));
                                } else {
                                    _state.setValue(new DeletePersonajePorFaccionState(null, null, res.get()));
                                }
                            },
                            error -> log.error("Error al obtener las facciones", error));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deletePersonajePorFaccion(int idFaccion) {
        deletePersonajePorFaccionUseCase.deletePersonajePorFaccion(idFaccion)
                .subscribe(res -> {
                    if (res.isLeft()) {
                        _state.setValue(new DeletePersonajePorFaccionState(null, new ApiError("Error al eliminar los personajes", LocalDateTime.now()), null));
                    } else {
                        loadAllFacciones();
                    }
                }, error -> log.error("Error al eliminar el personaje", error));

    }

    public ReadOnlyObjectProperty<DeletePersonajePorFaccionState> getState() {
        return _state;
    }

}
