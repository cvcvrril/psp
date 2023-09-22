package ui.pantallas.welcome;

import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;

public class WelcomeController extends BasePantallaController {


    private Label welcomeText;

    public void initialize(){
        welcomeText.setText("Welcome " + getPrincipalController().getUsuario());
    }

}
