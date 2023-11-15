package ui.pantallas.order.orderadd;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.MenuItem;
import model.Order;
import model.OrderItem;
import model.TableRestaurant;
import model.errors.ErrorCMenuItem;
import model.errors.ErrorCOrder;
import model.errors.ErrorCTables;
import services.SERVmenuItems;
import services.SERVorder;
import services.SERVorderItem;
import services.SERVtablesRestaurant;
import ui.pantallas.common.BasePantallaController;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddOrderController extends BasePantallaController {

    private final SERVorder serVorder;
    private final SERVtablesRestaurant serVtablesRestaurant;
    private final SERVmenuItems serVmenuItems;
    private final SERVorderItem serVorderItem;
    @FXML
    private TableView<OrderItem> mItemTable;
    @FXML
    private TableColumn<OrderItem, Integer> mItemIDCol;
    @FXML
    private TableColumn<OrderItem, String> mItemNameCol;
    @FXML
    private TableColumn<OrderItem, Integer> quantityCol;

    @FXML
    private ComboBox<Integer> customerComboBox;
    @FXML
    private ComboBox<Integer> tableComboBox;
    @FXML
    private ComboBox<String> menuItemsCBox;
    @FXML
    private TextField menuItemQuantity;

    @FXML
    private Button addOrderButton;
    @FXML
    private Button addItemButton;
    @FXML
    private Button removeItemButton;

    @Inject
    public AddOrderController(SERVorder serVorder, SERVtablesRestaurant serVtablesRestaurant, SERVmenuItems serVmenuItems, SERVorderItem serVorderItem) {
        this.serVorder = serVorder;
        this.serVtablesRestaurant = serVtablesRestaurant;
        this.serVmenuItems = serVmenuItems;
        this.serVorderItem = serVorderItem;
    }

    public void principalCargado() {
        List<Integer> customerIDs = serVorder.getCustomerIDs();
        customerComboBox.getItems().addAll(customerIDs);
        if (getPrincipalController().getActualCredential().getId() > 0) {
            customerComboBox.setVisible(false);
        }else {
            customerComboBox.setVisible(true);
        }
        List<MenuItem> menuItems = serVmenuItems.getAll().getOrElse(Collections.emptyList());
        for (MenuItem menuItem : menuItems ){
            menuItemsCBox.getItems().add(menuItem.getNameMItem());
        }
        Either<ErrorCTables, List<TableRestaurant>> result = serVtablesRestaurant.getAll();
        if (result.isRight()) {
            List<TableRestaurant> tables = result.get();
            for (TableRestaurant table : tables) {
                tableComboBox.getItems().add(table.getIdTable());
            }
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al obtener la lista de mesas");
            errorAlert.show();
        }
        mItemIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        mItemNameCol.setCellValueFactory(cellData -> {
            int menuItemId = cellData.getValue().getMenuItem();
            String menuItemName = getMenuItemNameById(menuItemId);
            return new SimpleStringProperty(menuItemName);
        });

    }

    @FXML
    public void addOrder(ActionEvent actionEvent) {
        int customerId;
        if (getPrincipalController().getActualCredential().getId() > 0) {
            customerId = getPrincipalController().getActualCredential().getId();
        } else {
            customerId = customerComboBox.getValue();
        }
        int tableId = tableComboBox.getValue();
        LocalDateTime orderDate = LocalDateTime.now();

        List<OrderItem> orderItems = new ArrayList<>(mItemTable.getItems());
        Order newOrder = new Order(null,orderDate,customerId, tableId, orderItems);

        Either<ErrorCOrder, Integer> saveResult = serVorder.add(newOrder);
        if (saveResult.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.THE_ORDER_HAS_BEEN_ADDED);
            a.show();
            clearFields();
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Error al agregar la orden");
            a.show();
        }
    }

    private void clearFields() {
        customerComboBox.getSelectionModel().clearSelection();
        tableComboBox.getSelectionModel().clearSelection();
    }

    public void addItem() {
        String selectedItemName = menuItemsCBox.getValue();
        int quantity = Integer.parseInt(menuItemQuantity.getText());

        MenuItem selectedMenuItem = null;
        for (MenuItem menuItem : serVmenuItems.getAll().getOrElse(Collections.emptyList())) {
            if (menuItem.getNameMItem().equals(selectedItemName)) {
                selectedMenuItem = menuItem;
                break;
            }
        }

        if (selectedMenuItem != null) {
            int lastOrderItemId = getLastOrderItemIdFromDatabase();
            OrderItem newOrderItem = new OrderItem(lastOrderItemId, 0, selectedMenuItem.getIdMItem(), quantity, serVmenuItems.get(lastOrderItemId).getOrNull());

            // Agregar el nuevo OrderItem a la tabla
            mItemTable.getItems().add(newOrderItem);
            menuItemsCBox.getSelectionModel().clearSelection();
            menuItemQuantity.clear();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Ítem de menú no encontrado");
            errorAlert.show();
        }
    }

    public void removeItem() {
        mItemTable.getItems().clear();
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
