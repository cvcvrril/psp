package ui.pantallas.customerdelete;

import common.Constantes;
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
    private Button delCustomer;

    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client, Integer> id_c;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;


    @Inject

    public DeleteCustomerController(PrincipalController principalController, DAOclientsIMP daOclientsIMP) {
        this.principalController = principalController;
        this.daOclientsIMP = daOclientsIMP;
    }


    public void delCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf(Constantes.USER_DELETED);

    }

    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));

        tableCustomers.getItems().addAll(daOclientsIMP.getClients());

    }

}
