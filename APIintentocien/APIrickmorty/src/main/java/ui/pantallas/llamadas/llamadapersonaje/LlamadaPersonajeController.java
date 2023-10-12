package ui.pantallas.llamadas.llamadapersonaje;

import common.Constantes;
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
        idC.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        status.setCellValueFactory(new PropertyValueFactory<>(Constantes.STATUS));
        filtroPersonaje.getItems().addAll(Constantes.NINGUNO, Constantes.PERSONAJES_FEMENINOS, Constantes.PERSONAJES_MUERTOS, Constantes.JOHNNY_DEPP);
        tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofit().getOrNull());
    }

    public void handleFilterSelection(ActionEvent event) {
        String selectedItem = (String) filtroPersonaje.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem.equals(Constantes.PERSONAJES_FEMENINOS)) {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofitFem().getOrNull());
            } else if (selectedItem.equals(Constantes.PERSONAJES_MUERTOS)) {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofitDead().getOrNull());
            } else if (selectedItem.equals(Constantes.JOHNNY_DEPP)) {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofitJD().getOrNull());
            } else {
                tablePersonajes.getItems().clear();
                tablePersonajes.getItems().addAll(loadPersonajeUsecase.llamadaRetrofit().getOrNull());
            }
        }
    }

}
