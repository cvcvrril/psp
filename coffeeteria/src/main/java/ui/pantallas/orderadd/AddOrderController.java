package ui.pantallas.orderadd;

import common.Constantes;
import dao.imp.DAOordersFICHERO;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import ui.pantallas.common.BasePantallaController;

public class AddOrderController extends BasePantallaController {

    private final DAOordersFICHERO daOordersFICHERO;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;

    @Inject
    public AddOrderController(DAOordersFICHERO daOordersFICHERO) {
        this.daOordersFICHERO = daOordersFICHERO;
    }

    public void addOrder() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_ORDER_HAS_BEEN_ADDED);
        a.show();
    }

    public void addItem(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_ADDED);
        a.show();
    }

    public void removeItem(ActionEvent actionEvent) {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_REMOVED);
        a.show();
    }
}
