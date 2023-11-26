package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
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
    private final GetAllPersonajesUseCase useCase;

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
    private TableView<Faccion> tablaFacciones;
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
        viewModel.loadPersonajes();
        viewModel.getState().addListener((observable, oldValue, newValue) ->
            rellena(newValue));
    }

    private void rellena(ListPersonajeState state){
        idPersonaje.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombrePersonaje.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        razaPersonaje.setCellValueFactory(new PropertyValueFactory<>("raza"));
        planetaPersonaje.setCellValueFactory(new PropertyValueFactory<>("planetaRes"));
        List<Personaje> personajes = state.getPersonajes();
        if (personajes != null) {
            tablaPersonajes.getItems().setAll(personajes);
            tablaPersonajes.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    List<Faccion> facciones = newValue.getFacciones();
                    idFaccion.setCellValueFactory(new PropertyValueFactory<>("id"));
                    nombreFaccion.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    if (facciones != null && !facciones.isEmpty()) {
                        tablaFacciones.getItems().setAll(facciones);
                    } else {
                        tablaFacciones.getItems().clear();
                    }
                }
            });
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("La lista de personajes está vacía");
            alert.getDialogPane().setId("alert");
            alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
            alert.showAndWait();
        }
    }
}
