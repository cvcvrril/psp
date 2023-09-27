package ui.pantallas.customerlist;

import common.Constantes;
import dao.imp.DAOclientsIMP;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import ui.pantallas.common.BasePantallaController;

public class CustomerListController extends BasePantallaController {

    private final DAOclientsIMP daOclientsIMP;

    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client, Integer> id_c;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;
    @FXML
    private TableColumn<Client,Integer> phoneNumber;
    @FXML
    private TableColumn<Client,String> email;


    /*Constructores*/

    @Inject
    public CustomerListController(DAOclientsIMP daOclientsIMP) {
        this.daOclientsIMP = daOclientsIMP;
    }

    /*Métodos*/

    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        tableCustomers.getItems().addAll(daOclientsIMP.getClients());

    }

}
