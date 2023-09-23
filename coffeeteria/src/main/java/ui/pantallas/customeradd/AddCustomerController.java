package ui.pantallas.customeradd;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class AddCustomerController extends BasePantallaController {
    private final PrincipalController principalController;

    @FXML
    public Button addCustomerButton;

    @Inject
    public AddCustomerController(PrincipalController principalController) {
        this.principalController = principalController;
    }


    public void addCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf("User added");
    }
}
