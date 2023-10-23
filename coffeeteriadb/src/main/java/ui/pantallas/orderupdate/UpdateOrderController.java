package ui.pantallas.orderupdate;

import common.Constantes;
import dao.imp.DAOorderFILE;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Order;
import model.errors.ErrorCOrder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateOrderController extends BasePantallaController {

    private final DAOorderFILE daOorderFILE;

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

    @Inject
    public UpdateOrderController(DAOorderFILE daOorderFILE) {
        this.daOorderFILE = daOorderFILE;
    }

    public void addItem() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_ADDED);
        a.show();
    }

    public void removeItem() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.THE_MENU_ITEM_HAS_BEEN_REMOVED);
        a.show();
    }

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrders.getItems().addAll(daOorderFILE.getAll().getOrNull());
        tableOrders.setOnMouseClicked(this::handleTable);
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

    public void updateOrder(){
        try {
            int orderId = Integer.parseInt(orderIdField.getText());
            int customerId = Integer.parseInt(customerField.getText());
            int tableId = Integer.parseInt(tableField.getText());
            LocalDate orderDate = LocalDate.parse(dateField.getText());
            LocalDateTime orderDateTime = orderDate.atStartOfDay();
            Order updatedOrder = new Order(orderId, orderDateTime, customerId, tableId);
            Either<ErrorCOrder, Integer> updateResult = daOorderFILE.update(updatedOrder);

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
        }

    }
}
