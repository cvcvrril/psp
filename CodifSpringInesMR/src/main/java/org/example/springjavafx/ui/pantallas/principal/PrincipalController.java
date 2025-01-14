package org.example.springjavafx.ui.pantallas.principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.example.springjavafx.utils.Constantes;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.ui.pantallas.Pantallas;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Log4j2
@Component
public class PrincipalController {
    private final ApplicationContext context;
    public Menu menuAccount;
    public javafx.scene.control.MenuItem menuAccountLogout;
    private Alert alert;
    @Getter
    @Setter
    private User user;
    @FXML
    public BorderPane root;


    public PrincipalController(ApplicationContext context) {
        this.context = context;

    }

    public void initialize() {
        menuAccount.setVisible(false);
        this.alert = new Alert(Alert.AlertType.NONE);
        cargarPantalla(Pantallas.LOGINREGISTRO.getRuta());
    }

    public Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(context::getBean);
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();
            root.setCenter(panePantalla);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return panePantalla;
    }

    public void sacarAlertError(String mensaje) {
        alert.setAlertType(Alert.AlertType.ERROR);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId(Constantes.ALERT);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constantes.BTN_OK);
        alert.showAndWait();
    }

    public void sacarAlertConf(String mensaje) {
        alert.setAlertType(Alert.AlertType.CONFIRMATION);
        alert.setContentText(mensaje);
        alert.getDialogPane().setId(Constantes.ALERT);
        alert.getDialogPane().lookupButton(ButtonType.OK).setId(Constantes.BTN_OK);
        alert.showAndWait();
    }


    @FXML
    private void actMenuAccount(ActionEvent event) {
        if (event.getSource().equals(Constantes.MENU_ACCOUNT_LOGOUT)) {
            logout();
        }
    }

    private void logout(){
        user = null;
        menuAccount.setVisible(false);
        cargarPantalla(Pantallas.LOGINREGISTRO.getRuta());
    }

}
