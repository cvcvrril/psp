package ui.pantallas.welcome;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import ui.pantallas.common.BasePantallaController;


public class WelcomeController extends BasePantallaController {

    @FXML
    private Label welcomeText;

    public void initialize(){
        welcomeText.setText("Bienvenido");
    }

}
