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

    @FXML
    private final PrincipalController principalController;


    @Inject
    public WelcomeController(PrincipalController principalController) {
        this.principalController = principalController;

    }

    public void principalCargado(){
        //welcomeText.setText("Welcome " + principalController.actCred.getUser());
        welcomeText.setText("Welcome root");
    }

    public void initialize(){

        //welcomeText.setText("Welcome root");
    }

}
