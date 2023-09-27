package ui.pantallas.customerupdate;

import common.Constantes;
import dao.imp.DAOclientsIMP;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;

public class UpdateCustomerController extends BasePantallaController {

    private final PrincipalController principalController;
    private final DAOclientsIMP daOclientsIMP;
    @FXML
    private TextField idCustomerField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;

    @FXML
    private TableView<Client> tableCustomers;
    @FXML
    private TableColumn<Client, Integer> id_c;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;
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
        principalController.sacarAlertConf(Constantes.CUSTOMER_UPDATED);
    }

    public void resetCustomer(ActionEvent actionEvent) {
        principalController.sacarAlertConf(Constantes.SUCCESFULLY_RESET);
    }

    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        tableCustomers.getItems().addAll(daOclientsIMP.getClients());
        tableCustomers.setOnMouseClicked(this::handleTable);
    }

    private void handleTable(MouseEvent event){
        if (event.getClickCount() == 1){
            Client selClient = tableCustomers.getSelectionModel().getSelectedItem();
            if (selClient != null) {
                idCustomerField.setText(String.valueOf(selClient.getId_c()));
                
            }
        }
    }
}
