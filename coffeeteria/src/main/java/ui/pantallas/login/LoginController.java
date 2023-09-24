package ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Credential;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.principal.PrincipalController;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginController extends BasePantallaController {

    public Credential credential;
    @FXML
    private final PrincipalController principalController;

    @FXML
    private TextField userText;
    @FXML
    private TextField passwdText;

    @Inject
    public LoginController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    @FXML
    private void Login(ActionEvent actionEvent) {
        if (userText.getText().equals("root") && passwdText.getText().equals("2dam")) {
            getPrincipalController().onLogin(userText.getText());
        } else {
            principalController.sacarAlertError("Usuario o contraseña incorrectos");
        }
    }

    /*Métodos*/
}
