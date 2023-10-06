package ui.pantallas.llamadas.llamadalugar;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class LlamadaLugarController extends BasePantallaController {

    private final PrincipalController principalController;

    @FXML
    private Button addCustomerButton;

    @Inject
    public LlamadaLugarController(PrincipalController principalController) {
        this.principalController = principalController;
    }


    public void addCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf(common.Constantes.USER_ADDED);
    }
}
