package ui.pantallas.orderadd;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Order;
import model.errors.ErrorCOrder;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.List;

public class AddOrderController extends BasePantallaController {

    private final SERVorder serVorder;
    @FXML
    private ComboBox<Integer> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;
    @FXML
    private TextField dateField;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;

    @Inject
    public AddOrderController(SERVorder serVorder) {
        this.serVorder = serVorder;
    }

    public void initialize() {
        List<Integer> customerIDs = serVorder.getCustomerIDs();
        customerComboBox.getItems().addAll(customerIDs);
        tableComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    @FXML
    public void addOrder(ActionEvent actionEvent) {
        int customerId = customerComboBox.getValue();
        int tableId = tableComboBox.getValue();
        LocalDate orderDate = LocalDate.parse(dateField.getText());
        Order newOrder = new Order(customerId, orderDate.atStartOfDay(), tableId,   getNextOrderId());
        Either<ErrorCOrder, Integer> saveResult = serVorder.add(newOrder);
        if (saveResult.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_ORDER_HAS_BEEN_ADDED);
            a.show();
            clearFields();
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Error al agregar la orden");
            a.show();
        }
    }

    private void clearFields() {
        customerComboBox.getSelectionModel().clearSelection();
        tableComboBox.getSelectionModel().clearSelection();
        dateField.clear();
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

    private int getNextOrderId() {
        List<Order> orders = serVorder.getAll();
        if (!orders.isEmpty()) {
            return orders.get(orders.size() - 1).getIdOrd() + 1;
        } else {
            return 1;
        }
    }

}
