package ui.pantallas.customerdelete;

import dao.imp.DAOclientsIMP;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class DeleteCustomerController extends BasePantallaController {

    private final PrincipalController principalController;
    private final DAOclientsIMP daOclientsIMP;

    @FXML
    public Button delCustomer;

    @FXML
    public TableView<Client> tableCustomers;
    @FXML
    public TableColumn<Client,Integer> id_c;
    @FXML
    public TableColumn<Client,String> firstName;
    @FXML
    public TableColumn<Client,String> secondName;


    @Inject

    public DeleteCustomerController(PrincipalController principalController, DAOclientsIMP daOclientsIMP) {
        this.principalController = principalController;
        this.daOclientsIMP = daOclientsIMP;
    }


    public void delCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf("User deleted");

    }

    public void initialize(){

        id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("SecondName"));

        tableCustomers.getItems().addAll(daOclientsIMP.getClients());

    }

}
