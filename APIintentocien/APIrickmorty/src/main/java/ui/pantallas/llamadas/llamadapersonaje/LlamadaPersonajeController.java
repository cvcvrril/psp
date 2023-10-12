package ui.pantallas.llamadas.llamadapersonaje;

import domain.modelo.MiPersonaje;
import domain.usecase.LoadPersonajeUsecase;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

import java.util.List;

public class LlamadaPersonajeController extends BasePantallaController {

    /*Atributos*/

    private final LoadPersonajeUsecase loadPersonajeUsecase;

    @FXML
    private TableView<MiPersonaje> tablePersonajes;
    @FXML
    private TableColumn<MiPersonaje, Integer> idC;
    @FXML
    private TableColumn<MiPersonaje, String> firstName;
    @FXML
    private TableColumn<MiPersonaje, String> status;
    @FXML
    private ComboBox<String> filtroPersonaje;


    /*Constructores*/
    @Inject
    public LlamadaPersonajeController(LoadPersonajeUsecase loadPersonajeUsecase) {
        this.loadPersonajeUsecase = loadPersonajeUsecase;
    }

    /*Métodos*/

    public void initialize() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        filtroPersonaje.getItems().addAll("Ninguno", "Personajes femeninos", "Personajes muertos", "Jhonny Depp");
        tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofit().getOrNull());
    }

    public void handleFilterSelection(ActionEvent event) {
        String selectedItem = (String) filtroPersonaje.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem.equals("Personajes femeninos")) {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofitFem().getOrNull());
            }
            if (selectedItem.equals("Personajes muertos")) {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofitDead().getOrNull());
            } else {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofit().getOrNull());
            }
        }
    }

}
