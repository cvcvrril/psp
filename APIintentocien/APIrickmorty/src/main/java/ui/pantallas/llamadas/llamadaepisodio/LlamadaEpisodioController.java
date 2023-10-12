package ui.pantallas.llamadas.llamadaepisodio;

import domain.modelo.MiEpisodio;
import domain.usecase.LoadEpisodioUsecase;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

public class LlamadaEpisodioController extends BasePantallaController {

    private final LoadEpisodioUsecase loadEpisodioUsecase;

    @FXML
    private TableView<MiEpisodio> tableEpisodios;
    @FXML
    private TableColumn<MiEpisodio,Integer> idE;
    @FXML
    private TableColumn<MiEpisodio, String> epName;
    @FXML
    private TableColumn<MiEpisodio, String> epCode;

    @Inject
    public LlamadaEpisodioController(LoadEpisodioUsecase loadEpisodioUsecase) {

        this.loadEpisodioUsecase = loadEpisodioUsecase;
    }

    public void initialize() {
        idE.setCellValueFactory(new PropertyValueFactory<>("id"));
        epName.setCellValueFactory(new PropertyValueFactory<>("name"));
        epCode.setCellValueFactory(new PropertyValueFactory<>("episode"));
        tableEpisodios.getItems().addAll(loadEpisodioUsecase.llamadaRetrofit().getOrNull());
    }
}
