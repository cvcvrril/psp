package cliente.ui.pantallas.welcome;
import cliente.ui.ConstantesUi;
import cliente.ui.pantallas.common.BasePantallaController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;



public class WelcomeController extends BasePantallaController {

    @FXML
    private Label welcomeText;

    @Override
    public void principalCargado(){
        welcomeText.setText(ConstantesUi.BIENVENIDO);
    }

}
