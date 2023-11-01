package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;

@Log4j2
public class PrincipalController extends BasePantallaController {

    @FXML
    private MenuItem menuCustomersAdd;
    @FXML
    private MenuItem menuCustomersUpdate;
    @FXML
    private Menu menuOrders;
    @FXML
    private MenuItem menuOrdersList;
    @FXML
    private MenuItem menuOrdersAdd;
    @FXML
    private MenuItem menuOrdersUpdate;
    @FXML
    private MenuItem menuOrdersDelete;
    @FXML
    private MenuItem menuCustomersDelete;
    @FXML
    private MenuItem menuCustomersList;
    @FXML
    private Menu menuOptions;
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @Getter
    private String user;
    @FXML
    private BorderPane root;

    @FXML
    private Menu menuCustomers;

    private final Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        alert.showAndWait();
    }

    public void sacarAlertConf(String mensaje) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId("alert");
        alert.getDialogPane().lookupButton(ButtonType.OK).setId("btn-ok");
        alert.showAndWait();
    }

    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void onLogin(Credential credential) {
        this.user = credential.getUserName();
        menuPrincipal.setVisible(true);
        if(credential.getId() < 0){
            cargarPantalla(Pantallas.WELCOME);
            menuOptions.setDisable(false);
            menuCustomers.setDisable(false);
            menuOrdersAdd.setDisable(true);
        } if (credential.getId() >0){
            cargarPantalla(Pantallas.WELCOME);
            menuOptions.setDisable(false);
            menuCustomers.setDisable(true);
            menuOrders.setDisable(false);
            menuOrdersList.setDisable(false);
            menuOrdersUpdate.setDisable(false);
            menuOrdersAdd.setDisable(false);
            menuOrdersDelete.setDisable(true);
        }
        cargarPantalla(Pantallas.WELCOME);
    }

    public void logout() {
        this.user = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void exit(ActionEvent actionEvent) {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    /*Menu Options*/
    @FXML
    private void actMenuOptions(ActionEvent actionEvent){
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "menuOptionsLogout":
                logout();
        }
    }

    /*Menu de customers*/
    @FXML
    private void actMenuCustomers(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuCustomersList":
                cargarPantalla(Pantallas.CUS_LIST);
                break;
            case "menuCustomersAdd":
                cargarPantalla(Pantallas.CUS_ADD);
                break;
            case "menuCustomersUpdate":
                cargarPantalla(Pantallas.CUS_UPDATE);
                break;
            case "menuCustomersDelete":
                cargarPantalla(Pantallas.CUS_DEL);
                break;
        }
    }

    /*Menu de orders*/

    @FXML
    private void actMenuOrders(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuOrdersList":
                cargarPantalla(Pantallas.OR_LIST);
                break;
            case "menuOrdersAdd":
                cargarPantalla(Pantallas.OR_ADD);
                break;
            case "menuOrdersUpdate":
                cargarPantalla(Pantallas.OR_UPDATE);
                break;
            case "menuOrdersDelete":
                cargarPantalla(Pantallas.OR_DEL);
                break;
        }
    }
}
