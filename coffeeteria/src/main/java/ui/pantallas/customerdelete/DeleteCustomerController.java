package ui.pantallas.customerdelete;

import common.Constantes;
import dao.imp.DAOclientsFICHERO;
import dao.imp.DAOclientsIMP;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import ui.pantallas.common.BasePantallaController;

public class DeleteCustomerController extends BasePantallaController {

    //private final DAOclientsIMP daOclientsIMP;
    private final DAOclientsFICHERO daOclientsFICHERO;

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
    @FXML
    private TableColumn<Client,String> email;
    @FXML
    private TableColumn<Client,Integer> phoneNumber;



    @Inject

    public DeleteCustomerController(DAOclientsFICHERO daOclientsFICHERO) {
        this.daOclientsFICHERO = daOclientsFICHERO;
        //this.daOclientsIMP = daOclientsIMP;
    }


    public void delCustomer() {
        Alert a = new Alert(Alert.AlertType.CONFIRMATION);
        a.setContentText(Constantes.USER_DELETED);
        a.show();

    }

    public void initialize() {

        id_c.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID_C));
        firstName.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRST_NAME));
        secondName.setCellValueFactory(new PropertyValueFactory<>(Constantes.SECOND_NAME));
        email.setCellValueFactory(new PropertyValueFactory<>(Constantes.EMAIL));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>(Constantes.PHONE_NUMBER));
        //tableCustomers.getItems().addAll(daOclientsIMP.getClients());
        tableCustomers.getItems().addAll(daOclientsFICHERO.getAll().getOrNull());

    }

}
