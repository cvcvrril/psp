package ui.pantallas.orderlist;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.xml.OrderItemXML;
import model.xml.OrdersXML;
import model.errors.ErrorCOrder;
import services.SERVclient;
import services.SERVorder;
import services.SERVorderItem;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class OrderListController extends BasePantallaController {

    private final SERVorder serVorder;
    private final SERVclient serVclient;
    private final SERVorderItem serVorderItem;

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
    @FXML
    private TextField customerNameField;
    @FXML
    private TableView<OrderItemXML> orderItemsTable;
    @FXML
    private TableColumn<OrderItemXML,String> orderItemName;
    @FXML
    private TableColumn<OrderItemXML, Integer> orderItemQuantity;

    /*Constructores*/

    @Inject
    public OrderListController(SERVorder serVorder, SERVclient serVclient, SERVorderItem serVorderItem) {
        this.serVorder = serVorder;
        this.serVclient = serVclient;
        this.serVorderItem = serVorderItem;
    }

    /*Métodos*/

    public void initialize() {
        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrders.getItems().addAll(serVorder.getAll());
        filterComboBox.getItems().addAll("Date", "Customer", "None");
        tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            customerNameField.getText();
            if (newSelection != null) {
                customerNameField.setText(serVclient.getClients(tableOrders.getSelectionModel().getSelectedItem().getIdCo()).get().getFirstName());
            }

        });
        tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int orderId = newSelection.getIdOrd();
                Either<ErrorCOrder, List<OrderItemXML>> result = connectOrderIdWithOrderItem(orderId);
                if (result.isRight()) {
                    orderItemsTable.getItems().setAll(result.get());
                } else {
                }
            }
        });
    }

    @FXML
    private void handleFilterSelection(ActionEvent event) {
        String selectedItem = (String) filterComboBox.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            if (selectedItem.equals("Date")) {
                LocalDate selectedDate = fechaDatePicker.getValue();
                List<Order> filteredOrders = serVorder.getOrdersByDate(selectedDate);
                updateTable(filteredOrders);
            } else if (selectedItem.equals("Customer")) {
                int selectedCustomerId = Integer.parseInt(customerField.getText());
                List<Order> filteredOrders = serVorder.getOrdersByCustomer(selectedCustomerId);
                updateTable(filteredOrders);
            } else if (selectedItem.equals("None")) {
                tableOrders.getItems().clear();
                tableOrders.getItems().addAll(serVorder.getAll());
                clearFields();
            }
        }
    }

    private void updateTable(List<Order> orders) {
        tableOrders.getItems().clear();
        tableOrders.getItems().addAll(orders);
    }

    private void clearFields() {
        customerField.clear();
        fechaDatePicker.setValue(null);
    }

    public Either<ErrorCOrder, List<OrderItemXML>> connectOrderIdWithOrderItem(int orderId) {
        Either<ErrorCOrder, OrdersXML> result = serVorderItem.getOrders(orderId);

        return result.map(ordersXML -> {
            List<OrderItemXML> orderItems = new ArrayList<>();
            orderItems.addAll(ordersXML.getOrderXMLList().get(0).getOrderItem());
            return orderItems;
        });
    }

}
