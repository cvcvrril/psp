package ui.pantallas.order.orderlist;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.errors.ErrorCOrderItem;
import services.SERVclient;
import services.SERVorder;
import services.SERVorderItem;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    private ComboBox<String> filterComboBox;
    @FXML
    private DatePicker fechaDatePicker;
    @FXML
    private TextField customerField;
    @FXML
    private TextField customerNameField;
    @FXML
    private TableView<OrderItem> orderItemsTable;
    @FXML
    private TableColumn<OrderItem, String> menuItemName;
    @FXML
    private TableColumn<OrderItem, Integer> orderItemQuantity;
    @FXML
    private TableColumn<OrderItem,Integer> orderItemID;

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
        tableOrders.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                int customerId = newSelection.getIdCo();
                Customer customer = serVclient.get(customerId).getOrNull();
                if (customer != null) {
                    customerNameField.setText(customer.getFirstName());
                }
                loadOrderItems(newSelection.getIdOrd());
            }
        });
        orderItemQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        orderItemID.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    private void handleFilterSelection(ActionEvent event) {
        String selectedItem =filterComboBox.getSelectionModel().getSelectedItem();
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

    public void hide(){
        if (Objects.equals(filterComboBox.getSelectionModel().getSelectedItem(), "Date")){
            fechaDatePicker.setVisible(true);
            customerField.setVisible(false);
        } else if (Objects.equals(filterComboBox.getSelectionModel().getSelectedItem(), "Customer")){
            fechaDatePicker.setVisible(false);
            customerField.setVisible(true);
        } else if (Objects.equals(filterComboBox.getSelectionModel().getSelectedItem(), "None")){
            fechaDatePicker.setVisible(false);
            customerField.setVisible(false);
        }
    }

    //Por alguna razón no me pilla los valores de los order_items

    public void setTableOrders() {
        try {
            Order selectedOrder = tableOrders.getSelectionModel().getSelectedItem();
            if (selectedOrder != null) {
                loadOrderItemsByOrderId(selectedOrder.getIdOrd());
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    private void loadOrderItems(int orderId) {
        Either<ErrorCOrderItem, List<OrderItem>> orderItems = serVorderItem.getOrders(orderId);
        if (orderItems.isRight()) {
            orderItemsTable.getItems().clear();
            orderItemsTable.getItems().addAll(orderItems.get());
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al mostrar los order items");
            errorAlert.show();
        }
    }

    private void loadOrderItemsByOrderId(int orderId) {
        Either<ErrorCOrderItem, List<OrderItem>> orderItems = serVorderItem.getOrders(orderId);
        if (orderItems.isRight()) {
            orderItemsTable.getItems().clear();
            orderItemsTable.getItems().addAll(orderItems.get());
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al mostrar los Order Items");
            errorAlert.show();
        }
    }
}
