package cliente.ui.pantallas.faccion;

import cliente.domain.usecases.DeletePersonajePorFaccionUseCase;
import cliente.domain.usecases.GetAllFaccionesUseCase;
import cliente.ui.ConstantesUi;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Faccion;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DeletePersonajePorFaccionController extends BasePantallaController {

    private DeletePersonajePorFaccionViewModel viewModel;
    private final DeletePersonajePorFaccionUseCase deleteUseCase;
    private final GetAllFaccionesUseCase allUseCase;


    @FXML
    private TableView<Faccion> tablaFacciones;
    @FXML
    private TableColumn<Faccion, Integer> idFaccionCol;
    @FXML
    private TableColumn<Faccion, String> nombreFaccionCol;

    @Inject
    public DeletePersonajePorFaccionController(DeletePersonajePorFaccionViewModel viewModel, DeletePersonajePorFaccionUseCase deleteUseCase, GetAllFaccionesUseCase allUseCase) {
        this.viewModel = viewModel;
        this.deleteUseCase = deleteUseCase;
        this.allUseCase = allUseCase;
    }

    @Override
    public void principalCargado() {
        viewModel = new DeletePersonajePorFaccionViewModel(allUseCase, deleteUseCase);
        viewModel.loadAllFacciones();
        viewModel.getState().addListener((observable, oldValue, newValue) ->
                rellena(newValue));

    }

    private void rellena(DeletePersonajePorFaccionState state) {
        idFaccionCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.ID));
        nombreFaccionCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.NOMBRE));
        List<Faccion> facciones = state.getFacciones();
        if (facciones != null) {
            tablaFacciones.getItems().setAll(facciones);
        }
    }

    @FXML
    private void deletePersonajes(){
        Faccion faccionSeleccionada = tablaFacciones.getSelectionModel().getSelectedItem();
        if (faccionSeleccionada!=null){
            viewModel.deletePersonajePorFaccion(faccionSeleccionada.getId());
        }
    }
}
