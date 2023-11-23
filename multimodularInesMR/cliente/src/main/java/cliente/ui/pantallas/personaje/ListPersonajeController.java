package cliente.ui.pantallas.personaje;

import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Faccion;
import domain.modelo.Personaje;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

public class ListPersonajeController extends BasePantallaController {

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
    





}
