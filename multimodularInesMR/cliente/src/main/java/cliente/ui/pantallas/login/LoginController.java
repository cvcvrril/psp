package cliente.ui.pantallas.login;

import cliente.domain.usecases.LoginUseCase;
import cliente.domain.usecases.RegistroUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Usuario;
import jakarta.inject.Inject;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends BasePantallaController {
    private LoginViewModel viewModel;
    private  final RegistroUseCase registroUseCase;
    private final LoginUseCase loginUseCase;

    @FXML
    private TextField userField;
    @FXML
    private TextField passField;

    @FXML
    private TextField newUserField;
    @FXML
    private TextField newPassField;


    @Inject
    public LoginController(RegistroUseCase registroUseCase, LoginUseCase loginUseCase) {
        this.registroUseCase = registroUseCase;
        this.loginUseCase = loginUseCase;
    }

    @Override
    public void principalCargado() {
        viewModel = new LoginViewModel(loginUseCase, registroUseCase);
        listener();
    }

    private void listener() {
        viewModel.getState().addListener((observable, oldValue, newValue) -> {
            Platform.runLater(() -> {
                if (newValue != null) {
                    getPrincipalController().onLogin(newValue.getUsuario());
                    viewModel.reset();
                }
            });
        });
    }

    public void login(){
        String username = userField.getText();
        String password = passField.getText();
        viewModel.login(username, password);
    }

    public void registro(){
        String nuevoUsername = newUserField.getText();
        String nuevoPass = newPassField.getText();
        if (!nuevoUsername.isEmpty() && !nuevoPass.isEmpty()) {
            Usuario nuevoUsuario = new Usuario(0, nuevoUsername, nuevoPass);
            viewModel.registro(nuevoUsuario);
            newPassField.clear();
            newUserField.clear();
        }
    }
}
