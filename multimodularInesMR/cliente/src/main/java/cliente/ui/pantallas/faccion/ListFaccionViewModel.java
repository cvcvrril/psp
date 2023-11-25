package cliente.ui.pantallas.faccion;

import cliente.domain.usecases.GetAllFaccionesUseCase;
import domain.errores.ApiError;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@Log4j2
public class ListFaccionViewModel {

    private final GetAllFaccionesUseCase getAllFaccionesUseCase;
    private final ObjectProperty<ListFaccionState> _state;

    @Inject
    public ListFaccionViewModel(GetAllFaccionesUseCase getAllFaccionesUseCase) {
        this.getAllFaccionesUseCase = getAllFaccionesUseCase;
        _state = new SimpleObjectProperty<>(new ListFaccionState(null, null));
    }

    public void loadFacciones() {
        try {
            getAllFaccionesUseCase.getAllFacciones()
                    .subscribe(res -> {
                            if (res.isLeft()){
                                _state.setValue(new ListFaccionState(null,new ApiError("Error al obtener las facciones", LocalDateTime.now())));
                            }else {
                                _state.setValue(new ListFaccionState(res.get(), null));
                            }

                            },
                            error -> log.error("Error al obtener los personajes", error));


        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

    }

    public ReadOnlyObjectProperty<ListFaccionState> getState() {
        return _state;
    }

}

