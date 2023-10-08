package ui.pantallas.llamadas.llamadapersonaje;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import domain.modelo.Client;
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
