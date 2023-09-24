package ui.pantallas.orderadd;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class AddOrderController extends BasePantallaController {


    private final PrincipalController principalController;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;

    @Inject
    public AddOrderController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void addOrder(ActionEvent actionEvent) { principalController.sacarAlertConf(Constantes.THE_ORDER_HAS_BEEN_ADDED);
    }

    public void addItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf(Constantes.THE_MENU_ITEM_HAS_BEEN_ADDED);
    }

    public void removeItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf(Constantes.THE_MENU_ITEM_HAS_BEEN_REMOVED);
    }
}
