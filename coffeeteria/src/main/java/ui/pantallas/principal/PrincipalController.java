package ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.extern.log4j.Log4j2;
import ui.pantallas.common.BasePantallaController;
import ui.pantallas.common.Pantallas;

import java.io.IOException;
import java.util.Optional;

@Log4j2
public class PrincipalController {

    @FXML
    private Menu menuHelp;
    // objeto especial para DI
    Instance<Object> instance;

    @FXML
    private MenuBar menuPrincipal;
    private Stage primaryStage;

    @FXML
    public BorderPane root;


    private final Alert alert;

    private Pane pantallaBienvenida;


    @Inject
    public PrincipalController(Instance<Object> instance) {
       this.instance = instance;
       alert= new Alert(Alert.AlertType.NONE);
    }

    private void cargarPantalla(Pantallas pantalla) {

        switch (pantalla) {
//            case LISTADO:
//                cambioPantalla(cargarPantalla(pantalla.getRuta()));
//                break;
//            case PANTALLA1:
//                if (pantallaBienvenida == null){
//                    pantallaBienvenida = cargarPantalla(pantalla.getRuta());
//                }
//
//                cambioPantalla(pantallaBienvenida);
//                break;
            default:
                cambioPantalla(cargarPantalla(pantalla.getRuta()));
                break;
        }
    }

    private Pane cargarPantalla(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BasePantallaController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);
            pantallaController.principalCargado();


        } catch (IOException e) {
            log.error(e.getMessage(),e);
        }
        return panePantalla;
    }

    private void cambioPantalla(Pane pantallaNueva) {
        root.setCenter(pantallaNueva);
    }


    public void initialize() {
        menuPrincipal.setVisible(false);
        cargarPantalla(Pantallas.LOGIN);

    }



    public void exit(ActionEvent actionEvent) {
//        primaryStage.close();
//        Platform.exit();
        primaryStage.fireEvent(new WindowEvent(primaryStage, WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    public void setStage(Stage stage) {
        primaryStage = stage;
    }

}
