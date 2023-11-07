package ui.pantallas.customer.customeradd;

import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Credential;
import model.Customer;
import model.errors.ErrorCCustomer;
import services.SERVcustomer;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;

public class AddCustomerController extends BasePantallaController {
    private final SERVcustomer serVcustomer;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField secondNameField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private Button addCustomerButton;

    @Inject
    public AddCustomerController(SERVcustomer serVcustomer) {
        this.serVcustomer = serVcustomer;
    }


    public void addCustomer() {

        int nextID = serVcustomer.getLastUsedId();
        String firstName = firstNameField.getText();
        String secondName = secondNameField.getText();
        String phone = phoneField.getText();
        String email = emailField.getText();
        String dateText = dateField.getText();
        int phoneNumber = Integer.parseInt(phone);
        LocalDate date = LocalDate.parse(dateText);
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (firstName.isEmpty() || secondName.isEmpty() || dateText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Los campos Nombre, Apellido y Fecha son obligatorios.");
            alert.showAndWait();
            return;
        }

        Customer newCustomer = new Customer(nextID,firstName, secondName, email,phoneNumber, date);
        Credential newCredential = new Credential(nextID, username, password);
        Either<ErrorCCustomer, Integer> res = serVcustomer.add(newCustomer, newCredential);
        clearFields();

        if (res.isRight()) {

            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(common.Constantes.USER_ADDED);
            a.show();
        }else {
            ErrorCCustomer error = res.getLeft();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText("Error al agregar el cliente");
            errorAlert.show();
        }
    }

    private void clearFields() {
        firstNameField.clear();
        secondNameField.clear();
        phoneField.clear();
        emailField.clear();
        dateField.clear();
    }
}
