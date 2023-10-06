package ui.pantallas.llamadas.llamadapersonaje;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import ui.pantallas.common.BasePantallaController;

public class LlamadaPersonajeController extends BasePantallaController {


    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client, Integer> id_c;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;

    /*Constructores*/


    /*Métodos*/

    public void initialize() {


    }

}
