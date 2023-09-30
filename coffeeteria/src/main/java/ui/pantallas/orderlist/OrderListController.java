package ui.pantallas.orderlist;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class OrderListController extends BasePantallaController {


    //private final DAOorderIMP daOorderIMP;
    //private final DAOordersFICHERO  daOordersFICHERO;
    private final SERVorder serVorder;

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

    /*Constructores*/

    @Inject
    public OrderListController(SERVorder serVorder) {
        this.serVorder = serVorder;
        //this.daOordersFICHERO = daOordersFICHERO;
    }

    /*Métodos*/

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        //tableOrders.getItems().addAll(daOorderIMP.getOrders());
        //tableOrders.getItems().addAll(daOordersFICHERO.getAll().getOrNull());
        tableOrders.getItems().addAll(serVorder.getAll());

    }

}
