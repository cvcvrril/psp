package cliente.ui.pantallas.welcome;
import cliente.ui.pantallas.common.BasePantallaController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class WelcomeController extends BasePantallaController {

    @FXML
    private Label welcomeText;

    public void principalCargado(){
        welcomeText.setText("Bienvenido");
    }

}
