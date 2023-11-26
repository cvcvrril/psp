package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.domain.usecases.GetPersonajeByIdUseCase;
import cliente.domain.usecases.UpdatePersonajeUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class UpdatePersonajeController extends BasePantallaController {

    private UpdatePersonajeViewModel viewModel;
    private final UpdatePersonajeUseCase useCaseUpdate;
    private final GetPersonajeByIdUseCase useCaseGet;
    private final GetAllPersonajesUseCase useCaseAll;

    @FXML
    private TableView<Personaje> tablaPersonaje;
    @FXML
    private TableColumn<Personaje, Integer> idPersonajeCol;
    @FXML
    private TableColumn<Personaje, String> nombrePersonajeCol;
    @FXML
    private TableColumn<Personaje, Integer> razaPersonajeCol;
    @FXML
    private TableColumn<Personaje, String> planetaResPersonajeCol;

    @FXML
    private TextField nombreField;
    @FXML
    private TextField planetaResidenciaField;

    @Inject
    public UpdatePersonajeController(UpdatePersonajeViewModel viewModel, UpdatePersonajeUseCase useCaseUpdate, GetPersonajeByIdUseCase useCaseGet, GetAllPersonajesUseCase useCaseAll) {
        this.viewModel = viewModel;
        this.useCaseUpdate = useCaseUpdate;
        this.useCaseGet = useCaseGet;
        this.useCaseAll = useCaseAll;
    }

    @Override
    public void principalCargado(){
        viewModel = new UpdatePersonajeViewModel(useCaseGet, useCaseAll, useCaseUpdate);
        viewModel.loadAllPersonajes();
        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            rellena(newValue);
        });
        actualizar();
        tablaPersonaje.setOnMouseClicked(event -> {
            Personaje personajeSeleccionado = tablaPersonaje.getSelectionModel().getSelectedItem();
            if (personajeSeleccionado != null) {
                nombreField.setText(personajeSeleccionado.getNombre());
                planetaResidenciaField.setText(personajeSeleccionado.getPlanetaRes());
            }
        });
    }

    private void rellena(UpdatePersonajeState state){
        idPersonajeCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombrePersonajeCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        razaPersonajeCol.setCellValueFactory(new PropertyValueFactory<>("raza"));
        planetaResPersonajeCol.setCellValueFactory(new PropertyValueFactory<>("planetaRes"));
        List<Personaje> personajes = state.getPersonajes();
        if (personajes != null) {
            tablaPersonaje.getItems().setAll(personajes);
        }
    }

    @FXML
    private void actualizar(){
        Personaje personajeSeleccionado = tablaPersonaje.getSelectionModel().getSelectedItem();
        if (personajeSeleccionado != null) {
            String nuevoNombre = nombreField.getText();
            String nuevoPlanetaRes = planetaResidenciaField.getText();
            personajeSeleccionado.setNombre(nuevoNombre);
            personajeSeleccionado.setPlanetaRes(nuevoPlanetaRes);
            viewModel.updatePersonaje(personajeSeleccionado);
            nombreField.clear();
            planetaResidenciaField.clear();
        }
    }
}
