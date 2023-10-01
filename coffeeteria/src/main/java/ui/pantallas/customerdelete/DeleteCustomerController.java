package ui.pantallas.customerdelete;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import model.Order;
import model.errors.ErrorCCustomer;
import services.SERVclient;
import services.SERVorder;
import ui.pantallas.common.BasePantallaController;

import java.util.List;
import java.util.Optional;

public class DeleteCustomerController extends BasePantallaController {

    //private final DAOclientsIMP daOclientsIMP;
    //private final DAOclientsFICHERO daOclientsFICHERO;
    private final SERVclient serVclient;
    private final SERVorder serVorder;

    @FXML
    private Button delCustomer;

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



    @Inject

    public DeleteCustomerController(SERVclient serVclient, SERVorder serVorder) {
        this.serVclient = serVclient;
        //this.daOclientsFICHERO = daOclientsFICHERO;
        //this.daOclientsIMP = daOclientsIMP;
        this.serVorder = serVorder;
    }


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
                tableCustomers.getItems().remove(selCustomer);
            } else {
                ErrorCCustomer error = res.getLeft();
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("Error al eliminar el cliente");
                errorAlert.show();
            }
        }
    }


    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        //tableCustomers.getItems().addAll(daOclientsIMP.getClients());
        //tableCustomers.getItems().addAll(daOclientsFICHERO.getAll().getOrNull());
        tableCustomers.getItems().addAll(serVclient.getClients().getOrNull());

    }

}
