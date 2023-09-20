package ui.main;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.pantallas.customerlist.ListController;

import java.io.IOException;
import java.util.Properties;
import java.util.ResourceBundle;

public class MainFX {

    //Es raro que un inyect esté así y no en un constructor
    @Inject
    FXMLLoader fxmlLoader;

    public void start(@Observes @StartupScene Stage stage) throws IOException {
        try {
//            ResourceBundle r = ResourceBundle.getBundle("/i18n/textos");

            //fxmlLoader.setResources(r);
            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream("/fxml/list.fxml"));
            ListController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(fxmlParent));
            //stage.setMinHeight(1000);
//            stage.getScene().getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
//            stage.setTitle(r.getString("app.title"));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
