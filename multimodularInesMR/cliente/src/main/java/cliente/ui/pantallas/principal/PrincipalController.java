package cliente.ui.pantallas.principal;


import cliente.ui.pantallas.common.BasePantallaController;
import cliente.ui.pantallas.common.Pantallas;
import domain.modelo.Usuario;
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



import java.io.IOException;

@Log4j2
public class PrincipalController extends BasePantallaController {

    @FXML
    private MenuItem menuLlamadasPersonajes;
    @FXML
    private Menu menuOptions;
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;


    @Getter
    private String username;
    @Getter
    private int loginId;
    @FXML
    private BorderPane root;

    @FXML
    private Menu menuLlamadas;

    private final Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
            default:
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
                break;
        }
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

    public void onLogin(Usuario usuario) {
        //this.username = usuario.getUsername();
        //this.loginId = usuario.getId();
        menuPrincipal.setVisible(true);
        cargarPantalla(Pantallas.WELCOME);
    }

    public void onLogout() {
        this.username = null;
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);
    }

    public void exit() {
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

    @FXML
    private void actMenuOptions(ActionEvent actionEvent){
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "menuOptionsLogout":
                onLogout();
                break;
        }
    }

    @FXML
    private void actMenuLlamadasPersonaje(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "menuListaPersonaje":
                cargarPantalla(Pantallas.LISTA_PERSONAJE);
                break;
            case "menuAddPersonaje":
                cargarPantalla(Pantallas.ADD_PERSONAJE);
                break;
            case "menuUpdatePersonaje":
                cargarPantalla(Pantallas.UPDATE_PERSONAJE);
                break;
            case "menuDeletePersonaje":
                cargarPantalla(Pantallas.DELETE_PERSONAJE);
                break;
        }
    }

    public void actMenuLlamadasRaza(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "menuListaRaza":
                cargarPantalla(Pantallas.LISTA_RAZA);
                break;
        }
    }

    public void actMenuLlamadasFaccion(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()){
            case "menuListaFaccion":
                cargarPantalla(Pantallas.LISTA_FACCION);
                break;
        }
    }
}
