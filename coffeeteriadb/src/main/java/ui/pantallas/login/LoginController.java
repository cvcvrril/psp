package ui.pantallas.login;

import common.Constantes;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Credential;
import services.SERVlogin;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginController extends BasePantallaController {

    @FXML
    private TextField userText;
    @FXML
    private TextField passwdText;
    private final SERVlogin serVlogin;

    @Inject
    public LoginController(SERVlogin serVlogin) {
        this.serVlogin = serVlogin;
    }

    @FXML
    private void Login() {
        Credential credential = new Credential(userText.getText(), passwdText.getText());
        if (serVlogin.doLogin(credential)) {
            getPrincipalController().onLogin(credential);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText(Constantes.USUARIO_O_CONTRASENA_INCORRECTOS);
            a.show();
        }
    }

    /*Métodos*/
}
