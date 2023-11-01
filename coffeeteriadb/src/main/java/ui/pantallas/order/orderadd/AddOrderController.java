package ui.pantallas.order.orderadd;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import model.Order;
import model.errors.ErrorCOrder;
import services.SERVorder;
import services.SERVtablesRestaurant;
import ui.pantallas.common.BasePantallaController;
import java.time.LocalDateTime;
import java.util.List;

public class AddOrderController extends BasePantallaController {

    private final SERVorder serVorder;
    private final SERVtablesRestaurant serVtablesRestaurant;
    @FXML
    private ComboBox<Integer> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;

    @Inject
    public AddOrderController(SERVorder serVorder, SERVtablesRestaurant serVtablesRestaurant) {
        this.serVorder = serVorder;
        this.serVtablesRestaurant = serVtablesRestaurant;
    }

    public void initialize() {
        List<Integer> customerIDs = serVorder.getCustomerIDs();
        customerComboBox.getItems().addAll(customerIDs);
        tableComboBox.getItems().addAll(1, 2, 3, 4, 5);
//        Either<ErrorCTables, List<TableRestaurant>> result = serVtablesRestaurant.getAll();
//        if (result.isRight()) {
//            List<TableRestaurant> tables = result.get();
//            for (TableRestaurant table : tables) {
//                tableComboBox.getItems().add(table.getIdTable());
//            }
//        } else {
//            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//            errorAlert.setContentText("Error al obtener la lista de mesas");
//            errorAlert.show();
//        }
    }

    @FXML
    public void addOrder(ActionEvent actionEvent) {
        int customerId = customerComboBox.getValue();
        int tableId = tableComboBox.getValue();
        LocalDateTime orderDate = LocalDateTime.now();

        Order newOrder = new Order( getNextOrderId() , orderDate,customerId, tableId);
        Either<ErrorCOrder, Integer> saveResult = serVorder.add(newOrder);
        if (saveResult.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_ORDER_HAS_BEEN_ADDED);
            a.show();
            clearFields();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error al agregar la orden");
            a.show();
        }
    }

    private void clearFields() {
        customerComboBox.getSelectionModel().clearSelection();
        tableComboBox.getSelectionModel().clearSelection();
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
