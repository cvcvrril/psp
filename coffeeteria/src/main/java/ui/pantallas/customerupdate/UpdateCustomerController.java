package ui.pantallas.customerupdate;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Client;
import model.errors.ErrorCCustomer;
import services.SERVclient;
import ui.pantallas.common.BasePantallaController;

public class UpdateCustomerController extends BasePantallaController {
    private final SERVclient serVclient;

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
    private TableColumn<Client, Integer> idC;
    @FXML
    private TableColumn<Client, String> firstName;
    @FXML
    private TableColumn<Client, String> secondName;
    @FXML
    private TableColumn<Client, String> email;
    @FXML
    private TableColumn<Client, Integer> phoneNumber;
    @FXML
    private Button resetCustomerButton;
    @FXML
    private Button updateCustomerButton;

    @Inject
    public UpdateCustomerController(SERVclient serVclient) {
        this.serVclient = serVclient;
    }

    public void updateCustomer() {
        int id = Integer.parseInt(idCustomerField.getText());
        String firstName = firstNameField.getText();
        String secondName = secondNameField.getText();
        String email = emailField.getText();
        int phoneNumber = Integer.parseInt(phoneField.getText());

        Client updatedClient = new Client(id, firstName, secondName, email, phoneNumber, null);

        Either<ErrorCCustomer, Integer> res = serVclient.updateClient(updatedClient);
        if (res.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.CUSTOMER_UPDATED);
            a.show();
            resetFields();
            tableCustomers.getItems().clear();
            tableCustomers.getItems().addAll(serVclient.getClients().getOrNull());
        } else {
            ErrorCCustomer error = res.getLeft();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al actualizar el cliente");
            errorAlert.show();
        }
    }

    public void resetCustomer() {
        resetFields();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.SUCCESFULLY_RESET);
        a.show();
    }

    private void resetFields() {
        idCustomerField.clear();
        firstNameField.clear();
        secondNameField.clear();
        emailField.clear();
        phoneField.clear();
    }

    public void initialize() {

        idC.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        tableCustomers.getItems().addAll(serVclient.getClients().getOrNull());
        tableCustomers.setOnMouseClicked(this::handleTable);
    }

    private void handleTable(MouseEvent event){
        if (event.getClickCount() == 1){
            Client selClient = tableCustomers.getSelectionModel().getSelectedItem();
            if (selClient != null) {
                idCustomerField.setText(String.valueOf(selClient.getId_c()));
                firstNameField.setText(String.valueOf(selClient.getFirstName()));
                secondNameField.setText(String.valueOf(selClient.getSecondName()));
                emailField.setText(String.valueOf(selClient.getEmail()));
                phoneField.setText(String.valueOf(selClient.getPhoneNumber()));
            }
        }
    }


}
