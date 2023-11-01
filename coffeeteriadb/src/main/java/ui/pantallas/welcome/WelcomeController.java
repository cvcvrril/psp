package ui.pantallas.welcome;

import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.login.LoginController;
import ui.pantallas.principal.PrincipalController;

public class WelcomeController extends BasePantallaController {


    @FXML
    private Label welcomeText;

    @Inject
    public WelcomeController(PrincipalController principalController) {

    }

    public void initialize(){
        welcomeText.setText("Welcome root");
    }

}
