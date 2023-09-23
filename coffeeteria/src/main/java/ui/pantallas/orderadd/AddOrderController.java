package ui.pantallas.orderadd;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class AddOrderController extends BasePantallaController {

    private final PrincipalController principalController;

    @FXML
    public Button addOrderButton;
    @FXML
    public Button addItemButton;
    @FXML
    public Button removeItemButton;

    @Inject
    public AddOrderController(PrincipalController principalController) {
        this.principalController = principalController;
    }
    
    public void addOrder(ActionEvent actionEvent) {
        principalController.sacarAlertConf("The order has been added");
    }

    public void addItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf("The menu item has been added");
    }

    public void removeItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf("The menu item has been removed");
    }
}
