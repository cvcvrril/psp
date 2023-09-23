package ui.pantallas.orderupdate;

import dao.imp.DAOorderIMP;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

import java.time.LocalDate;

public class UpdateOrderController extends BasePantallaController {

    private final PrincipalController principalController;
    private final DAOorderIMP daOorderIMP;

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order,Integer> id_ord;
    @FXML
    private TableColumn<Order,Integer> id_c;
    @FXML
    private TableColumn<Order,Integer> id_table;
    @FXML
    private TableColumn<Order, LocalDate> date_order;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;
    @FXML
    private Button updateOrderButton;

    @Inject
    public UpdateOrderController(PrincipalController principalController, DAOorderIMP daOorderIMP) {
        this.principalController = principalController;
        this.daOorderIMP = daOorderIMP;
    }

    public void addItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Item added");
    }

    public void removeItem(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Item removed");
    }

    public void updateOrder(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Order updated");
    }

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>("id_ord"));
        id_c.setCellValueFactory(new PropertyValueFactory<>("id_co"));
        id_table.setCellValueFactory(new PropertyValueFactory<>("id_table"));
        date_order.setCellValueFactory(new PropertyValueFactory<>("or_date"));

        tableOrders.getItems().addAll(daOorderIMP.getOrders());

    }

}
