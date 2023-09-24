package ui.pantallas.orderdelete;

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

public class DeleteOrderController extends BasePantallaController {

    private final PrincipalController principalController;
    private final DAOorderIMP daOorderIMP;

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order, Integer> id_ord;
    @FXML
    private TableColumn<Order, Integer> id_c;
    @FXML
    private TableColumn<Order, Integer> id_table;
    @FXML
    private TableColumn<Order, LocalDate> date_order;
    @FXML
    private Button delOrderButton;

    @Inject
    public DeleteOrderController(PrincipalController principalController, DAOorderIMP daOorderIMP) {
        this.principalController = principalController;
        this.daOorderIMP = daOorderIMP;
    }

    public void delOrder(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Order deleted");
    }

    public void initialize() {

        id_ord.setCellValueFactory(new PropertyValueFactory<>("id_ord"));
        id_c.setCellValueFactory(new PropertyValueFactory<>("id_co"));
        id_table.setCellValueFactory(new PropertyValueFactory<>("id_table"));
        date_order.setCellValueFactory(new PropertyValueFactory<>("or_date"));

        tableOrders.getItems().addAll(daOorderIMP.getOrders());

    }
}
