package ui.pantallas.order.orderupdate;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.errors.ErrorCMenuItem;
import services.SERVmenuItems;
import services.SERVorder;
import services.SERVorderItem;
import ui.pantallas.common.BasePantallaController;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateOrderController extends BasePantallaController {

    private final SERVorder serVorder;
    private final SERVorderItem serVorderItem;
    private final SERVmenuItems serVmenuItems;

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
    private ComboBox<String> menuItemComboBox;

    @FXML
    private TableView<OrderItem> orderItemTable;
    @FXML
    private TableColumn<OrderItem, String> nameItemCell;
    @FXML
    private TableColumn<OrderItem, Integer> quantityCell;
    private int lastOrderItemId;

    @Inject
    public UpdateOrderController(SERVorder serVorder, SERVorderItem serVorderItem, SERVmenuItems serVmenuItems) {
        this.serVorder = serVorder;
        this.serVorderItem = serVorderItem;
        this.serVmenuItems = serVmenuItems;
    }

    @Override
    public void principalCargado() {
        //CAMBIATE TODO LO DEL INITIALIZE AL PRINCIPAL CARGADO PLOX
        lastOrderItemId = serVorderItem.getAll().get().size() + 1;
        //Order Table Columns
        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        //Llenar OrderTable
        if (getPrincipalController().getActualCredential().getId() > 0) {
            tableOrders.getItems().addAll(serVorder.getOrder(this.getPrincipalController().getActualCredential().getId()).getOrNull());
        } else {
            tableOrders.getItems().addAll(serVorder.getAll());
        }
        //Llenar los campos al clickar y llenar Items table
        tableOrders.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderItemTable.getItems().clear();
                Order selOrder = tableOrders.getSelectionModel().getSelectedItem();
                if (selOrder != null) {
                    orderIdField.setText(String.valueOf(selOrder.getIdOrd()));
                    customerField.setText(String.valueOf(selOrder.getIdCo()));
                    tableField.setText(String.valueOf(selOrder.getIdTable()));
                    dateField.setText(String.valueOf(selOrder.getOrDate()));
                }
                orderItemTable.getItems().addAll(serVorderItem.get(tableOrders.getSelectionModel().getSelectedItem().getIdOrd()).getOrNull());
            }
        });
        //Columnas Item table
        quantityCell.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        nameItemCell.setCellValueFactory(cellData -> {
            int menuItemId = cellData.getValue().getMenuItem();
            String menuItemName = getMenuItemNameById(menuItemId);
            return new SimpleStringProperty(menuItemName);
        });
        //Llenar los campos de OrderItem
        orderItemTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                orderItemTable.getSelectionModel().getSelectedItem().setMenuItemObject(serVmenuItems.get(orderItemTable.getSelectionModel().getSelectedItem().getMenuItem()).getOrNull());
                menuItemComboBox.setValue(orderItemTable.getSelectionModel().getSelectedItem().getMenuItemObject().getNameMItem());
                quantityField.setText(String.valueOf(orderItemTable.getSelectionModel().getSelectedItem().getQuantity()));
            }
        });
        //ComboBox
        ObservableList<String> observableList = FXCollections.observableArrayList();
        for (MenuItem menuItem : serVmenuItems.getAll().getOrNull()) {
            observableList.add(menuItem.getNameMItem());
        }
        menuItemComboBox.getItems().addAll(observableList);
    }

    public void addItem() {
        String selectedItemName = menuItemComboBox.getSelectionModel().getSelectedItem();
        int quantity = Integer.parseInt(quantityField.getText());
        MenuItem selectedMenuItem = serVmenuItems.getMenuItemByName(selectedItemName).getOrNull();
        for (MenuItem menuItem : serVmenuItems.getAll().getOrElse(Collections.emptyList())) {
            if (menuItem.getNameMItem().equals(selectedItemName)) {
                selectedMenuItem = menuItem;
            }
        }
        if (selectedMenuItem != null) {

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_ADDED);
            a.show();
            // Agregar el nuevo OrderItem a la tabla
            orderItemTable.getItems().add(new OrderItem(lastOrderItemId, tableOrders.getSelectionModel().getSelectedItem().getIdOrd(), selectedMenuItem.getIdMItem(), quantity, serVmenuItems.get(lastOrderItemId).getOrNull()));
            menuItemComboBox.getSelectionModel().clearSelection();
            quantityField.clear();
            lastOrderItemId = lastOrderItemId + 1;
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Ítem de menú no encontrado");
            errorAlert.show();
        }


    }

    public void removeItem() {
        orderItemTable.getItems().remove(orderItemTable.getSelectionModel().getSelectedItem());
        quantityField.clear();
        lastOrderItemId = lastOrderItemId - 1;
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_REMOVED);
        a.show();
    }

    public void updateOrder() {
        //Cambia toda tu puta mierda de alerts para usar el controller pls
        // Y AÑADE LOS DEMAS ALERTS DE MI CODIGO (PRINCIPAL CONTROLLER)
        //this.getPrincipalController().sacarAlertError("Puta tu madre");
        Order selectedOrder = tableOrders.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            selectedOrder.setIdCo(Integer.parseInt(customerField.getText()));
            selectedOrder.setIdTable(Integer.parseInt(tableField.getText()));
            serVorder.updateOrder(selectedOrder);
            if (serVorderItem.get(selectedOrder.getIdOrd()) != null) {
                serVorderItem.delete(selectedOrder.getIdOrd());
                //Aqui miras mi codigo en el dao de order items y te haces el insert y delete by order
                //y en este if te haces el delete
            }
            List<OrderItem> orderItems =new ArrayList<>(orderItemTable.getItems());
            serVorderItem.add(orderItems, selectedOrder.getIdOrd());
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.ORDER_UPDATED);
            a.show();

            //Aqui te haces la lista de order items y los insertas todo MIRA MI CODIGO!!!
        }
        orderItemTable.getItems().clear();
        orderItemTable.getItems().addAll(serVorderItem.getAll().getOrNull());
    }

    public String getMenuItemNameById(int id) {
        Either<ErrorCMenuItem, String> result = serVmenuItems.getMenuItemName(id);
        return result.get();
    }
}
