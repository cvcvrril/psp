package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
import org.example.springjavafx.ui.pantallas.principal.PrincipalController;
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

    public LoginRegistroController(ServiciosUsuarios servicios) {
        this.servicios = servicios;
    }

    @FXML
    private void doLogin(){
        String usernameLogin = usernameLoginField.getText();
        String passwordLogin = passwordLoginField.getText();
        if (usernameLogin.isEmpty() || passwordLogin.isEmpty()){
            getPirncipalController().sacarAlertError("Hay campos vacíos.");
        }else{
            User usuarioLogin = servicios.doLogin(new User(UUID.randomUUID(), usernameLogin, passwordLogin, new ArrayList<>())).get();
            if (usuarioLogin!= null){
                getPirncipalController().setUser(usuarioLogin);
                usernameLoginField.clear();
                passwordLoginField.clear();
                getPirncipalController().cargarPantalla(Pantallas.PROGRAMASPERMISOS.getRuta());
            } else {
                getPirncipalController().sacarAlertError("Usuario o contraseña incorrectos.");
            }
        }

    }

    @FXML
    private void doRegistro(){
        String username = usernameRegistroField.getText();
        String password = passwordRegistroField.getText();
        if (username.isEmpty() || password.isEmpty()){
            getPirncipalController().sacarAlertError("Hay campos vacíos.");
        }else{
            User nuevoUsuario = new User(UUID.randomUUID(),username,password, new ArrayList<>());
            servicios.addUser(nuevoUsuario);
            getPirncipalController().sacarAlertConf("Usuario añadido correctamente.");
            usernameRegistroField.clear();
            passwordRegistroField.clear();
        }
    }

    @Override
    public void principalCargado() {

    }
}
