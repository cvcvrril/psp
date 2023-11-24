package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import cliente.ui.pantallas.principal.PrincipalController;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class ListPersonajeController extends BasePantallaController {

    private ListPersonajeViewModel viewModel;
    private GetAllPersonajesUseCase useCase;

    @FXML
    private TableView<Personaje> tablaPersonajes;
    @FXML
    private TableColumn<Integer, Personaje> idPersonaje;
    @FXML
    private TableColumn<String, Personaje> nombrePersonaje;
    @FXML
    private TableColumn<Integer,Personaje> razaPersonaje;
    @FXML
    private TableColumn<String, Personaje> planetaPersonaje;

    @FXML
    private TableColumn<Integer, Faccion> idFaccion;
    @FXML
    private TableColumn<String, Faccion> nombreFaccion;

    @Inject
    public ListPersonajeController(ListPersonajeViewModel viewModel, GetAllPersonajesUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado(){
        viewModel = new ListPersonajeViewModel(useCase);
        initialize();
    }

    private void initialize(){
        idPersonaje.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombrePersonaje.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        razaPersonaje.setCellValueFactory(new PropertyValueFactory<>("raza"));
        planetaPersonaje.setCellValueFactory(new PropertyValueFactory<>("planeta_residencia"));
        List<Personaje> personajes = viewModel.getState().get().getPersonajes();
        if (personajes != null) {
            tablaPersonajes.getItems().addAll(personajes);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Error al mostrar los personajes");
            alert.getDialogPane().setId("alert");
            alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
            alert.showAndWait();
        }
    }
}
