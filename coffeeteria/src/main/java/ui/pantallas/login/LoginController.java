package ui.pantallas.login;

import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;

@Log4j2
public class LoginController extends BasePantallaController {

    @FXML
    private MenuItem menuOptionsExit;
    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @FXML
    public BorderPane root;
}
