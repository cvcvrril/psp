package ui.pantallas.orderdelete;

import dao.imp.DAOorderIMP;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    public TableView<Order> tableOrders;
    @FXML
    public TableColumn<Order,Integer> id_ord;
    @FXML
    public TableColumn<Order,Integer> id_c;
    @FXML
    public TableColumn<Order,Integer> id_table;
    @FXML
    public TableColumn<Order, LocalDate> date_order;

    @Inject
    public DeleteOrderController(PrincipalController principalController, DAOorderIMP daOorderIMP) {
        this.principalController = principalController;
        this.daOorderIMP = daOorderIMP;
    }

    public void delOrder(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Order deleted");
    }

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>("id_ord"));
        id_c.setCellValueFactory(new PropertyValueFactory<>("id_co"));
        id_table.setCellValueFactory(new PropertyValueFactory<>("id_table"));
        date_order.setCellValueFactory(new PropertyValueFactory<>("or_date"));

        tableOrders.getItems().addAll(daOorderIMP.getOrders());

    }
}
