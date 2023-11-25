package cliente.ui.pantallas.faccion;

import cliente.domain.usecases.GetAllFaccionesUseCase;
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
    public ListFaccionController(ListFaccionViewModel viewModel,GetAllFaccionesUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado(){
        viewModel = new ListFaccionViewModel(useCase);
        viewModel.loadFacciones();
        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            rellena(newValue);
        });
    }

    private void rellena(ListFaccionState state){
        idFaccion.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreFaccion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        List<Faccion> facciones = state.getFacciones();
        if (facciones!=null){
            tablaFaccion.getItems().setAll(facciones);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La lista de facciones está vacía");
            alert.getDialogPane().setId("alert");
            alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
            alert.showAndWait();
        }
    }

}
