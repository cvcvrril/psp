package cliente.ui.pantallas.raza;

import cliente.domain.usecases.GetAllRazasUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Raza;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListRazaController extends BasePantallaController {


    private ListRazaViewModel viewModel;
    private final GetAllRazasUseCase useCase;

    @FXML
    private TableView<Raza> tablaRazas;
    @FXML
    private TableColumn<Raza, Integer> idRaza;
    @FXML
    private TableColumn<Raza, String> nombreRaza;
    @FXML
    private TableColumn<Raza, String> planetaOrigenRaza;

    @Inject
    public ListRazaController(ListRazaViewModel viewModel, GetAllRazasUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado(){
        viewModel = new ListRazaViewModel(useCase);
        viewModel.loadRazas();
        viewModel.getState().addListener((observale, oldValue, newValue)->{
            rellena(newValue);
        });
    }

    private void rellena(ListRazaState state){
        idRaza.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreRaza.setCellValueFactory(new PropertyValueFactory<>("nombreRaza"));
        planetaOrigenRaza.setCellValueFactory(new PropertyValueFactory<>("planetaOrigen"));
        List<Raza> razas = state.getRazas();
        if (razas!=null){
            tablaRazas.getItems().setAll(razas);
        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La lista de razas está vacía");
            alert.getDialogPane().setId("alert");
            alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
            alert.showAndWait();
        }
    }

}
