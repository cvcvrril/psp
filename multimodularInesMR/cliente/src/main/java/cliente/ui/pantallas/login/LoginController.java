package cliente.ui.pantallas.login;

import cliente.domain.usecases.LoginUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends BasePantallaController {

    private LoginViewModel viewModel;
    private final LoginUseCase useCase;


    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    @Inject
    public LoginController(LoginViewModel viewModel, LoginUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado(){
        viewModel = new LoginViewModel(useCase);
    }

    @FXML
    private void login(){
        String username = userField.getText();
        String password = passField.getText();
        viewModel.login(username,password);
    }

}
