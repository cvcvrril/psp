package ui.pantallas.llamadas.llamadapersonaje;

import domain.modelo.MiPersonaje;
import domain.usecase.LoadPersonajeUsecase;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

public class LlamadaPersonajeController extends BasePantallaController {

    /*Atributos*/

    private final LoadPersonajeUsecase loadPersonajeUsecase;



    @FXML
    private TableView<MiPersonaje> tableCustomers;
    @FXML
    private  TableColumn<MiPersonaje, Integer> idC;
    @FXML
    private TableColumn<MiPersonaje, String> firstName;


    /*Constructores*/
    @Inject
    public LlamadaPersonajeController(LoadPersonajeUsecase loadPersonajeUsecase) {
        this.loadPersonajeUsecase = loadPersonajeUsecase;
    }

    /*Métodos*/

    public void initialize() {
        idC.setCellValueFactory(new PropertyValueFactory<>("id"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableCustomers.getItems().addAll(loadPersonajeUsecase.llamadaRetrofit().getOrNull());

    }

}
