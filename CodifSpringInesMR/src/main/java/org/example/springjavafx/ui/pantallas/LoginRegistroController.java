package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.springframework.stereotype.Component;

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

    private final ServiciosUsuarios servicios;


    public LoginRegistroController(ServiciosUsuarios servicios) {
        this.servicios = servicios;
    }

}
