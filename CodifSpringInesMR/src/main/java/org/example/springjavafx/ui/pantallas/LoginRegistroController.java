package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.springjavafx.common.Constantes;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.servicios.ServiciosClaves;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class LoginRegistroController extends BasePantallaController {
    @FXML
    private Button loginButton;
    @FXML
    private Button registroButton;
    @FXML
    private PasswordField passwordLoginField;
    @FXML
    private TextField usernameLoginField;
    @FXML
    private TextField usernameRegistroField;
    @FXML
    private PasswordField passwordRegistroField;
    private final ServiciosUsuarios servicios;
    private final ServiciosClaves claves;

    public LoginRegistroController(ServiciosUsuarios servicios, ServiciosClaves claves) {
        this.servicios = servicios;
        this.claves = claves;
    }

    @FXML
    private void doLogin(){
        String usernameLogin = usernameLoginField.getText();
        String passwordLogin = passwordLoginField.getText();
        if (usernameLogin.isEmpty() || passwordLogin.isEmpty()){
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        }else{
            User usuarioLogin = servicios.doLogin(new User(UUID.randomUUID(), usernameLogin, passwordLogin, new ArrayList<>())).get();
            if (usuarioLogin!= null){
                getPrincipalController().setUser(usuarioLogin);
                usernameLoginField.clear();
                passwordLoginField.clear();
                getPrincipalController().cargarPantalla(Pantallas.PROGRAMASPERMISOS.getRuta());
            } else {
                getPrincipalController().sacarAlertError(Constantes.USUARIO_O_CONTRASENA_INCORRECTOS);
            }
        }

    }

    @FXML
    private void doRegistro(){
        String username = usernameRegistroField.getText();
        String password = passwordRegistroField.getText();
        if (username.isEmpty() || password.isEmpty()){
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        }else{
            User nuevoUsuario = new User(UUID.randomUUID(),username,password, new ArrayList<>());
            servicios.addUser(nuevoUsuario);
            claves.generateUserPrivatePublicKey(nuevoUsuario.getName());
            getPrincipalController().sacarAlertConf(Constantes.USUARIO_ANADIDO_CORRECTAMENTE);
            usernameRegistroField.clear();
            passwordRegistroField.clear();
        }
    }

    @Override
    public void principalCargado() {

    }
}
