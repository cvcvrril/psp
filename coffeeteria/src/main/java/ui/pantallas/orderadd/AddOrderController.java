package ui.pantallas.orderadd;

import common.Constantes;
import dao.imp.DAOordersFICHERO;
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
    //private final DAOordersFICHERO daOordersFICHERO;
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
        customerComboBox.getItems().addAll(1,2,3,4,5);
        tableComboBox.getItems().addAll(1,2,3,4,5,6,7,8);
    }

    @FXML
    public void addOrder(ActionEvent actionEvent) {
        int customerId = customerComboBox.getValue();
        int tableId = tableComboBox.getValue();
        LocalDate orderDate = LocalDate.parse(dateField.getText()); // Asume que se ingresa la fecha en el formato correcto

        Order newOrder = new Order(customerId, getNextOrderId(), tableId, orderDate);

        // Guardar la nueva orden
        Either<ErrorCOrder, Integer> saveResult = serVorder.saveOrder(newOrder);
        if (saveResult.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_ORDER_HAS_BEEN_ADDED);
            a.show();
            // Limpia los campos después de agregar la orden
            customerComboBox.getSelectionModel().clearSelection();
            tableComboBox.getSelectionModel().clearSelection();
            dateField.clear();
        } else {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText("Error al agregar la orden");
            a.show();
        }


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
            return orders.get(orders.size() - 1).getId_ord() + 1;
        } else {
            return 1;
        }
    }

}
