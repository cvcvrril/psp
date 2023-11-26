package cliente.ui.pantallas.faccion;

import cliente.domain.usecases.GetAllFaccionesUseCase;
import cliente.ui.ConstantesUi;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Faccion;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListFaccionController extends BasePantallaController {

    private ListFaccionViewModel viewModel;
    private final GetAllFaccionesUseCase useCase;

    @FXML
    private TableView<Faccion> tablaFaccion;
    @FXML
    private TableColumn<Faccion, Integer> idFaccion;
    @FXML
    private TableColumn<Faccion, String> nombreFaccion;

    @Inject
    public ListFaccionController(ListFaccionViewModel viewModel, GetAllFaccionesUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado() {
        viewModel = new ListFaccionViewModel(useCase);
        viewModel.loadFacciones();
        viewModel.getState().addListener((observable, oldValue, newValue) ->
                rellena(newValue));
    }

    private void rellena(ListFaccionState state) {
        idFaccion.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.ID));
        nombreFaccion.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.NOMBRE));
        List<Faccion> facciones = state.getFacciones();
        if (facciones != null) {
            tablaFaccion.getItems().setAll(facciones);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(ConstantesUi.LA_LISTA_DE_FACCIONES_ESTÁ_VACIA);
            alert.getDialogPane().setId(ConstantesUi.ALERT);
            alert.getDialogPane().lookupButton(ButtonType.OK).setId(ConstantesUi.BTN_OK);
            alert.showAndWait();
        }
    }

}
