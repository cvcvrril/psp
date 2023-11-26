package cliente.ui.pantallas.faccion;

import domain.modelo.Faccion;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class DeletePersonajePorFaccionController {

    @FXML
    private TableView<Faccion> tablaFacciones;
    @FXML
    private TableColumn<Faccion, Integer> idFaccionCol;
    @FXML
    private TableColumn<Faccion, String> nombreFaccionCol;

    

}
