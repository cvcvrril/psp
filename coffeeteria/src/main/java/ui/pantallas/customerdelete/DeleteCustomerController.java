package ui.pantallas.customerdelete;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
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
    private TableColumn<Order, Integer> id_ord;
    @FXML
    private TableColumn<Order,Integer> id_c2;
    @FXML
    private TableColumn<Order,Integer> id_table;
    @FXML
    private TableColumn<Order, LocalDate> date_order;

    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client, Integer> id_c;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;
    @FXML
    private TableColumn<Client,String> email;
    @FXML
    private TableColumn<Client,Integer> phoneNumber;
    @FXML
    private TableColumn<Client,LocalDate> date;



    @Inject

    public DeleteCustomerController(SERVclient serVclient, SERVorder serVorder) {
        this.serVclient = serVclient;
        this.serVorder = serVorder;
    }

    //TODO arreglar que me elimina lo primero

    public void delCustomer() {
        Client selCustomer = tableCustomers.getSelectionModel().getSelectedItem();
        if (selCustomer != null) {
            // Verificar si el cliente tiene órdenes asociadas
            List<Order> customerOrders = serVorder.getOrdersByCustomer(selCustomer.getId_c());
            if (!customerOrders.isEmpty()) {
                // Si el cliente tiene órdenes, preguntar al usuario si desea eliminarlo
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setContentText("Este cliente tiene órdenes asociadas. ¿Desea eliminarlo de todos modos?");
                Optional<ButtonType> result = confirmationAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                    return; // El usuario canceló la eliminación
                }
            }

            Either<ErrorCCustomer, Integer> res = serVclient.delClient(selCustomer.getId_c());
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
        id_c2.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_CO));
        id_ord.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_ORD));
        id_table.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_TABLE));
        date_order.setCellValueFactory(new PropertyValueFactory<>(Constantes.OR_DATE));
        tableOrdersCus.getItems().addAll(serVorder.getAll());
    }

    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        date.setCellValueFactory(new PropertyValueFactory<>(Constantes.DATE));
        tableCustomers.getItems().addAll(serVclient.getClients().getOrNull());
        tableCustomers.setOnMouseClicked(this::setTableOrdersCus);

    }

}
