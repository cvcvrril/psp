package ui.pantallas.customerlist;

import dao.imp.DAOclientsIMP;
import jakarta.inject.Inject;
import javafx.collections.ListChangeListener;
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

    /*Constructores*/

    @Inject
    public CustomerListController(DAOclientsIMP daOclientsIMP) {
        this.daOclientsIMP = daOclientsIMP;
    }

    /*Métodos*/

    public void initialize(){

       id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
       firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
       secondName.setCellValueFactory(new PropertyValueFactory<>("SecondName"));

       tableCustomers.getItems().addAll(daOclientsIMP.getClients());

    }

}
