package ui.pantallas.customeradd;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class AddCustomerController extends BasePantallaController {


    @FXML
    private Button addCustomerButton;

    public void addCustomer() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(common.Constantes.USER_ADDED);
        a.show();
    }
}
