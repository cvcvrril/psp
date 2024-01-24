package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.PrincipalController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class LoginRegistroController{
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

    private final PrincipalController principal;
    private final ServiciosUsuarios servicios;

    public LoginRegistroController(PrincipalController principal, ServiciosUsuarios servicios) {
        this.principal = principal;
        this.servicios = servicios;
    }

    private void doLogin(){


    }

    @FXML
    private void doRegistro(){
        String username = usernameRegistroField.getText();
        String password = passwordRegistroField.getText();
        if (username.isEmpty() || password.isEmpty()){
            System.out.println("tu puta madre");
        }else{
            User nuevoUsuario = new User(UUID.randomUUID(),username,password, new ArrayList<>());
            servicios.addUser(nuevoUsuario);
            usernameRegistroField.clear();
            passwordRegistroField.clear();
        }
    }
}
