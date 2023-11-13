package ui.pantallas.customer.customerupdate;

import common.Constantes;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Customer;
import model.errors.ErrorCCustomer;
import services.SERVcustomer;
import ui.pantallas.common.BasePantallaController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateCustomerController extends BasePantallaController {
    private final SERVcustomer serVcustomer;
    public DatePicker dateFieldd;

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
    private TextField dateField;

    @FXML
    private TableView<Customer> tableCustomers;
    @FXML
    private TableColumn<Customer, Integer> idC;
    @FXML
    private TableColumn<Customer, String> firstName;
    @FXML
    private TableColumn<Customer, String> secondName;
    @FXML
    private TableColumn<Customer, String> email;
    @FXML
    private TableColumn<Customer, Integer> phoneNumber;
    @FXML
    private TableColumn<Customer, LocalDate> date;
    @FXML
    private Button resetCustomerButton;
    @FXML
    private Button updateCustomerButton;

    @Inject
    public UpdateCustomerController(SERVcustomer serVcustomer) {
        this.serVcustomer = serVcustomer;
    }

    public void updateCustomer() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        int idCus = Integer.parseInt(idCustomerField.getText());
        String firstNameCus = firstNameField.getText();
        String secondNameCus = secondNameField.getText();
        String emailCus = emailField.getText();
        int phoneNumberCus = Integer.parseInt(phoneField.getText());
        LocalDate dateText = dateFieldd.getValue();

        Customer updatedCustomer = new Customer(idCus, firstNameCus, secondNameCus, emailCus, phoneNumberCus, dateText);

        Either<ErrorCCustomer, Integer> res = serVcustomer.update(updatedCustomer);
        if (res.isRight()) {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setContentText(Constantes.CUSTOMER_UPDATED);
            a.show();
            resetFields();
            tableCustomers.getItems().clear();
            tableCustomers.getItems().addAll(serVcustomer.getAll().getOrNull());

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
        date.setCellValueFactory(new PropertyValueFactory<>(Constantes.DATE));
        tableCustomers.getItems().addAll(serVcustomer.getAll().getOrNull());
        tableCustomers.setOnMouseClicked(this::handleTable);
    }

    private void handleTable(MouseEvent event){
        if (event.getClickCount() == 1){
            Customer selCustomer = tableCustomers.getSelectionModel().getSelectedItem();
            if (selCustomer != null) {
                idCustomerField.setText(String.valueOf(selCustomer.getIdC()));
                firstNameField.setText(String.valueOf(selCustomer.getFirstName()));
                secondNameField.setText(String.valueOf(selCustomer.getSecondName()));
                emailField.setText(String.valueOf(selCustomer.getEmailCus()));
                phoneField.setText(String.valueOf(selCustomer.getPhoneNumber()));
                dateFieldd.setValue(selCustomer.getDateBirth());
            }
        }
    }


}
