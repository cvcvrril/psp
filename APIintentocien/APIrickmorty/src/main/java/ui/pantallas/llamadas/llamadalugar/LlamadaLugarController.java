package ui.pantallas.llamadas.llamadalugar;

import common.Constantes;
import domain.modelo.MiLugar;
import domain.usecase.LoadLugarUsecase;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ui.pantallas.common.BasePantallaController;

public class LlamadaLugarController extends BasePantallaController {

    /*Atributos*/

    private final LoadLugarUsecase loadLugarUsecase;

    @FXML
    private TableView<MiLugar> tableLugares;
    @FXML
    private TableColumn<MiLugar,Integer> idL;
    @FXML
    private TableColumn<MiLugar,String> nombre;
    @FXML
    private TableColumn<MiLugar,String> tipo;
    @FXML
    private TableColumn<MiLugar,String> dimension;

    /*Constructor*/

    @Inject
    public LlamadaLugarController(LoadLugarUsecase loadLugarUsecase) {
        this.loadLugarUsecase = loadLugarUsecase;
    }

    /*Métodos*/

    public void initialize(){
        idL.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nombre.setCellValueFactory(new PropertyValueFactory<>(Constantes.NAME));
        tipo.setCellValueFactory(new PropertyValueFactory<>(Constantes.TYPE));
        dimension.setCellValueFactory(new PropertyValueFactory<>(Constantes.DIMENSION));
        tableLugares.getItems().addAll(loadLugarUsecase.llamadaRetrofit().getOrNull());

    }

}
