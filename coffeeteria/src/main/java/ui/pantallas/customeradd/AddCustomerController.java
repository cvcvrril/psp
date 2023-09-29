package ui.pantallas.customeradd;

import dao.imp.DAOclientsFICHERO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;

public class AddCustomerController extends BasePantallaController {

    private final DAOclientsFICHERO daOclientsFICHERO;

    @FXML
    private Button addCustomerButton;

    public AddCustomerController(DAOclientsFICHERO daOclientsFICHERO) {
        this.daOclientsFICHERO = daOclientsFICHERO;
    }

    public void addCustomer() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(common.Constantes.USER_ADDED);
        a.show();
    }
}
