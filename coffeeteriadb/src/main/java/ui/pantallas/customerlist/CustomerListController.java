package ui.pantallas.customerlist;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import services.SERVclient;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class CustomerListController extends BasePantallaController {

    private final SERVclient serVclient;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> idC;
    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, String> secondName;
    @FXML
    private TableColumn<Customer,Integer> phoneNumber;
    @FXML
    private TableColumn<Customer,String> email;
    @FXML
    private TableColumn<Customer, LocalDate> date;


    /*Constructores*/

    @Inject
    public CustomerListController(SERVclient serVclient) {
        this.serVclient = serVclient;
    }

    /*Métodos*/

    public void initialize() {

        idC.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        date.setCellValueFactory(new PropertyValueFactory<>(Constantes.DATE));
//        tableCustomers.getItems().addAll(serVclient.getClients().getOrNull());
        tableCustomers.getItems().addAll(serVclient.getAll().getOrNull());

    }

}
