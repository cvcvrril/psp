package ui.pantallas.customerupdate;

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

public class UpdateCustomerController extends BasePantallaController {

    private final PrincipalController principalController;
    private final DAOclientsIMP daOclientsIMP;

    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client,Integer> id_c;
    @FXML
    private TableColumn<Client,String> firstName;
    @FXML
    private TableColumn<Client,String> secondName;
    @FXML
    private Button resetCustomerButton;
    @FXML
    private Button updateCustomerButton;

    @Inject
    public UpdateCustomerController(PrincipalController principalController, DAOclientsIMP daOclientsIMP) {
        this.principalController = principalController;
        this.daOclientsIMP = daOclientsIMP;
    }

    public void updateCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Customer updated");
    }

    public void resetCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf("Succesfully reset");
    }

    public void initialize(){

        id_c.setCellValueFactory(new PropertyValueFactory<>("id_c"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        secondName.setCellValueFactory(new PropertyValueFactory<>("SecondName"));

        tableCustomers.getItems().addAll(daOclientsIMP.getClients());

    }
}
