package ui.pantallas.order.orderupdate;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.ErrorCMenuItem;
import model.errors.ErrorCOrder;
import services.SERVmenuItems;
import services.SERVorder;
import services.SERVorderItem;
import ui.pantallas.common.BasePantallaController;

import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Collections;
import java.util.List;

public class UpdateOrderController extends BasePantallaController {

    private final SERVorder serVorder;
    private final SERVorderItem serVorderItem;
    private final SERVmenuItems serVmenuItems;

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
    @FXML
    private TextField orderIdField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField tableField;
    @FXML
    private TextField customerField;
    @FXML
    private TextField quantityField;
    @FXML
    private  ComboBox<String> menuItemComboBox;

    @FXML
    private TableView<OrderItem> orderItemTable;
    @FXML
    private TableColumn<OrderItem, String> nameItemCell;
    @FXML
    private TableColumn<OrderItem, Integer> quantityCell;

    @Inject
    public UpdateOrderController(SERVorder serVorder, SERVorderItem serVorderItem, SERVmenuItems serVmenuItems) {
        this.serVorder = serVorder;
        this.serVorderItem = serVorderItem;
        this.serVmenuItems = serVmenuItems;
    }

    public void addItem() {
        String selectedItemName = menuItemComboBox.getValue();
        int quantity = Integer.parseInt(quantityField.getText());
        MenuItem selectedMenuItem = null;
        for (MenuItem menuItem : serVmenuItems.getAll().getOrElse(Collections.emptyList())) {
            if (menuItem.getNameMItem().equals(selectedItemName)) {
                selectedMenuItem = menuItem;
                break;
            }
        }
        if (selectedMenuItem != null) {
            int lastOrderItemId = getLastOrderItemIdFromDatabase();
            OrderItem newOrderItem = new OrderItem(lastOrderItemId, 0, selectedMenuItem.getIdMItem(), quantity, serVmenuItems.getListMenuItems(lastOrderItemId).getOrNull());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_ADDED);
            a.show();

            // Agregar el nuevo OrderItem a la tabla
            orderItemTable.getItems().add(newOrderItem);
            menuItemComboBox.getSelectionModel().clearSelection();
            quantityField.clear();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Ítem de menú no encontrado");
            errorAlert.show();
        }


    }

    public void removeItem() {
        OrderItem selectedItem = orderItemTable.getSelectionModel().getSelectedItem();
        if (selectedItem!= null){
            orderItemTable.getItems().remove(selectedItem);
            quantityField.clear();
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_REMOVED);
            a.show();
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Please, select one of the order items from the table to remove.");
            alert.show();
        }
    }

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrders.getItems().addAll(serVorder.getAll());
        tableOrders.setOnMouseClicked(this::handleTable);
        tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                updateOrderItemsTable(newValue);
            }
        });
        quantityCell.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        nameItemCell.setCellValueFactory(cellData -> {
            int menuItemId = cellData.getValue().getMenuItem();
            String menuItemName = getMenuItemNameById(menuItemId);
            return new SimpleStringProperty(menuItemName);
        });
        orderItemTable.setOnMouseClicked(this::handleorderItemsTable);
        List<MenuItem> menuItems = serVmenuItems.getAll().getOrElse(Collections.emptyList());
        for (MenuItem menuItem: menuItems){
            menuItemComboBox.getItems().add(menuItem.getNameMItem());
        }

    }

    private void handleTable(MouseEvent event){
        if (event.getClickCount() == 1){
            Order selOrder = tableOrders.getSelectionModel().getSelectedItem();
            if (selOrder != null){
                orderIdField.setText(String.valueOf(selOrder.getIdOrd()));
                customerField.setText(String.valueOf(selOrder.getIdCo()));
                tableField.setText(String.valueOf(selOrder.getIdTable()));
                dateField.setText(String.valueOf(selOrder.getOrDate()));
            }
        }
    }

    private void handleorderItemsTable(MouseEvent event){
        if (event.getClickCount() == 1){
            OrderItem selOrderItem = orderItemTable.getSelectionModel().getSelectedItem();
            if (selOrderItem != null){
                quantityField.setText(String.valueOf(selOrderItem.getQuantity()));
            }
        }
    }

    public void updateOrder(){
        try {
            int orderId = Integer.parseInt(orderIdField.getText());
            int customerId = Integer.parseInt(customerField.getText());
            int tableId = Integer.parseInt(tableField.getText());
            String dateText = dateField.getText();
            LocalDateTime orderDateTime = LocalDateTime.parse(dateText);
            List<OrderItem> orderItemList = orderItemTable.getItems();

            Order updatedOrder = new Order(orderId, orderDateTime, customerId, tableId, orderItemList);

            Either<ErrorCOrder, Integer> updateResult = serVorder.updateOrder(updatedOrder);

            if (updateResult.isRight()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Order updated successfully");
                alert.show();
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Failed to update order: " + updateResult.getLeft());
                alert.show();
            }
        }catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid input data");
            alert.show();
        }catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid date and time format. Please use the format 'YYYY-MM-DDTHH:mm'.");
            alert.show();
        }

    }

    public void updateOrderItemsTable(Order order) {
        List<OrderItem> orderItems = serVorderItem.getOrders(order.getIdOrd()).getOrElse(Collections.emptyList());
        orderItemTable.getItems().setAll(orderItems);
    }

    public String getMenuItemNameById(int id) {
        Either<ErrorCMenuItem, String> result = serVmenuItems.getMenuItemName(id);
        if(result.isRight()) {
            return result.get();
        } else {
            return null;
        }
    }

    private int getLastOrderItemIdFromDatabase() {
        List<OrderItem> orderItems = serVorderItem.getAll().getOrElse(Collections.emptyList());

        int lastOrderItemId = 0;

        for (OrderItem orderItem : orderItems) {
            if (orderItem.getId() > lastOrderItemId) {
                lastOrderItemId = orderItem.getId();
            }
        }

        return lastOrderItemId;
    }
}
