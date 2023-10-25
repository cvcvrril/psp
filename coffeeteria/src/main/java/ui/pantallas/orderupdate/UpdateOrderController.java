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
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class UpdateOrderController extends BasePantallaController {

    private final DAOorderFILE daOorderFILE;
    private final SERVorder serVorder;

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order,Integer> id_ord;
    @FXML
    private TableColumn<Order,Integer> id_co;
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
    public UpdateOrderController(DAOorderFILE daOorderFILE, SERVorder serVorder) {
        this.daOorderFILE = daOorderFILE;
        this.serVorder = serVorder;
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

    public void updateOrder() {
        int idCu = Integer.parseInt(id_co.getText());
        int id = Integer.parseInt(id_ord.getText());
        int idT = Integer.parseInt(id_table.getText());
        LocalDate dateOrd = LocalDate.parse(date_order.getText());

        Order updatedOrder = new Order(idCu, id, idT, dateOrd);

        Either<ErrorCOrder, Integer> res = serVorder.updateOrder(updatedOrder);
        if (res.isRight()){
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.ORDER_UPDATED);
            a.show();
            tableOrders.getItems().clear();
            tableOrders.getItems().addAll(serVorder.getAll());
        }else {
            ErrorCOrder error = res.getLeft();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al actualizar el order");
            errorAlert.show();
        }

    }

    public void initialize(){

        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_co.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
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

}
