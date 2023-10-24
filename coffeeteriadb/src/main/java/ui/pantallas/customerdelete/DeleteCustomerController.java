package ui.pantallas.customerdelete;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.Order;
import model.errors.ErrorCCustomer;
import services.SERVclient;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class DeleteCustomerController extends BasePantallaController {

    private final SERVclient serVclient;
    private final SERVorder serVorder;

    @FXML
    private Button delCustomer;

    @FXML
    private  TableView<Order> tableOrdersCus;
    @FXML
    private TableColumn<Order, Integer> idOrd;
    @FXML
    private TableColumn<Order,Integer> idC2;
    @FXML
    private TableColumn<Order,Integer> idTable;
    @FXML
    private TableColumn<Order, LocalDate> dateOrder;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> idC;
    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, String> secondName;
    @FXML
    private TableColumn<Customer,String> email;
    @FXML
    private TableColumn<Customer,Integer> phoneNumber;
    @FXML
    private TableColumn<Customer,LocalDate> date;



    @Inject

    public DeleteCustomerController(SERVclient serVclient, SERVorder serVorder) {
        this.serVclient = serVclient;
        this.serVorder = serVorder;
    }

    public void delCustomer() {
        boolean conf = false;
        Customer selCustomer = tableCustomers.getSelectionModel().getSelectedItem();
        if (selCustomer != null) {
            List<Order> customerOrders = serVorder.getOrdersByCustomer(selCustomer.getIdC());
            if (!customerOrders.isEmpty()) {
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setContentText("Este cliente tiene órdenes asociadas. ¿Desea eliminarlo de todos modos?");
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    conf = false;
                    return;
                } else {
                    conf = true;
                }
            }

            Either<ErrorCCustomer, Integer> res = serVclient.delete(selCustomer.getIdC(), conf);
            if (res.isRight()) {
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText(Constantes.USER_DELETED);
                a.show();
            } else {
                ErrorCCustomer error = res.getLeft();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error al eliminar el cliente");
                errorAlert.show();
            }
            tableCustomers.getItems().remove(selCustomer);
        }
    }

    public void setTableOrdersCus(MouseEvent event){
        tableOrdersCus.getItems().clear();
        idC2.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        idOrd.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        idTable.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        dateOrder.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrdersCus.getItems().addAll(serVorder.getAll());
    }

    public void initialize() {

        idC.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        date.setCellValueFactory(new PropertyValueFactory<>(Constantes.DATE));
        tableCustomers.getItems().addAll(serVclient.getAll().getOrNull());
        tableCustomers.setOnMouseClicked(this::setTableOrdersCus);

    }

}
