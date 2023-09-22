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

    private DAOclientsIMP daOclientsIMP;

    @FXML
    public TableView<Client> tableCustomers;
    @FXML
    public TableColumn<Client, Integer> id_c;
    @FXML
    public TableColumn<Client, String> firstName;
    @FXML
    public TableColumn<Client, String> secondName;

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
