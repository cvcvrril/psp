package cliente.ui.pantallas.faccion;

import domain.modelo.Faccion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ListFaccionController {

    @FXML
    private TableView<Faccion> tablaFaccion;
    @FXML
    private TableColumn<Faccion, Integer> idFaccion;
    @FXML
    private TableColumn<Faccion, String> nombreFaccion;
}
