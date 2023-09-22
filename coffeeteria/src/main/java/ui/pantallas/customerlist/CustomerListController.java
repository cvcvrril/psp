package ui.pantallas.customerlist;

import dao.imp.DAOclientsIMP;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Client;
import ui.pantallas.common.BasePantallaController;

public class CustomerListController extends BasePantallaController {


    private DAOclientsIMP daOclientsIMP;

    public TableColumn<Client, Integer> id_c;
    public TableColumn<Client, String> firstName;
    public TableColumn<Client, String> secondName;

    /*Constructores*/

    public CustomerListController(DAOclientsIMP daOclientsIMP) {
        this.daOclientsIMP = daOclientsIMP;
    }

    /*Métodos*/

    public void initialize(){

        id_c.setCellValueFactory(new PropertyValueFactory<>("Customer ID"));
    }

}
