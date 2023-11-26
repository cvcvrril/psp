package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.DeletePersonajeUseCase;
import cliente.domain.usecases.GetAllPersonajesUseCase;
import cliente.ui.ConstantesUi;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class DeletePersonajeController extends BasePantallaController {

    private DeletePersonajeViewModel viewModel;
    private final DeletePersonajeUseCase deleteUseCase;
    private final GetAllPersonajesUseCase allUseCase;
    
    @FXML
    private TableView<Personaje> tablaPersonajes;
    @FXML
    private TableColumn<Personaje, Integer> idPersonajeCol;
    @FXML
    private TableColumn<Personaje, String> nombrePersonajeCol;
    @FXML
    private TableColumn<Personaje, Integer> razaPersonajeCol;
    @FXML
    private TableColumn<Personaje, String> planetaResPersonajeCol;

    @Inject
    public DeletePersonajeController(DeletePersonajeViewModel viewModel, DeletePersonajeUseCase deleteUseCase, GetAllPersonajesUseCase allUseCase) {
        this.viewModel = viewModel;
        this.deleteUseCase = deleteUseCase;
        this.allUseCase = allUseCase;
    }
    
    @Override
    public void principalCargado(){
        viewModel = new DeletePersonajeViewModel(allUseCase, deleteUseCase);
        viewModel.loadAllPersonajes();
        viewModel.getState().addListener((observable, oldValue, newValue) ->
                rellena(newValue));
        
    }

    private void rellena(DeletePersonajeState state) {
        idPersonajeCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.ID));
        nombrePersonajeCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.NOMBRE));
        razaPersonajeCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.RAZA));
        planetaResPersonajeCol.setCellValueFactory(new PropertyValueFactory<>(ConstantesUi.PLANETA_RES));
        List<Personaje> personajes = state.getPersonajes();
        if (personajes != null) {
            tablaPersonajes.getItems().setAll(personajes);
        }
    }

    @FXML
    private void delete(){
        Personaje personajeSeleccionado = tablaPersonajes.getSelectionModel().getSelectedItem();
        if (personajeSeleccionado!=null){
            viewModel.deletePersonaje(personajeSeleccionado.getId());
        }
    }
}
