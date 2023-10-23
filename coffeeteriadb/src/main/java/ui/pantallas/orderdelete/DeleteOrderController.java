package ui.pantallas.orderdelete;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Order;
import model.errors.ErrorCOrder;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class DeleteOrderController extends BasePantallaController {

    private final SERVorder serVorder;

    @FXML
    private TableView<Order> tableOrders;
    @FXML
    private TableColumn<Order, Integer> idOrd;
    @FXML
    private TableColumn<Order, Integer> idC;
    @FXML
    private TableColumn<Order, Integer> idTable;
    @FXML
    private TableColumn<Order, LocalDate> dateOrder;
    @FXML
    private Button delOrderButton;

    @Inject
    public DeleteOrderController(SERVorder serVorder) {
        this.serVorder = serVorder;
    }

    public void delOrder(ActionEvent actionEvent) {
        Order selectedOrder = tableOrders.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            Either<ErrorCOrder, Integer> deleteResult = serVorder.delOrder(selectedOrder.getIdOrd());
            if (deleteResult.isRight()) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText(Constantes.ORDER_DELETED);
                a.show();
                tableOrders.getItems().remove(selectedOrder);
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error deleting order: " + deleteResult.getLeft());
                errorAlert.show();
            }
        } else {
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setContentText("Please select an order to delete.");
            infoAlert.show();
        }
    }

    public void initialize() {

        idOrd.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        idC.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        idTable.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        dateOrder.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrders.getItems().addAll(serVorder.getAll());

    }
}
