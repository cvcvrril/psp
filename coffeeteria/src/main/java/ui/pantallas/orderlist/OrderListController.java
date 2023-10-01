package ui.pantallas.orderlist;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.List;

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
    @FXML
    private ComboBox filterComboBox;
    @FXML
    private DatePicker fechaDatePicker;
    @FXML
    private TextField customerField;

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
        filterComboBox.getItems().addAll("Date", "Customer");
    }

    @FXML
    private void handleFilterSelection(ActionEvent event) {
        String selectedItem = (String) filterComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem.equals("Date")) {
                // Filtrar por fecha
                // Obtener la fecha seleccionada (puedes usar otro ComboBox o DatePicker)
                LocalDate selectedDate = fechaDatePicker.getValue(); // Aquí deberías obtener la fecha seleccionada

                // Filtrar la lista de pedidos por la fecha seleccionada
                List<Order> filteredOrders = serVorder.getOrdersByDate(selectedDate);
                updateTable(filteredOrders);
            } else if (selectedItem.equals("Customer")) {
                // Filtrar por cliente
                // Obtener el cliente seleccionado (puedes usar otro ComboBox)
                int selectedCustomerId = Integer.parseInt(customerField.getText()); // Aquí deberías obtener el ID del cliente seleccionado

                // Filtrar la lista de pedidos por el cliente seleccionado
                List<Order> filteredOrders = serVorder.getOrdersByCustomer(selectedCustomerId);
                updateTable(filteredOrders);
            }
        }
    }

    private void updateTable(List<Order> orders) {
        // Limpiar la tabla
        tableOrders.getItems().clear();

        // Agregar los pedidos filtrados a la tabla
        tableOrders.getItems().addAll(orders);
    }

}
