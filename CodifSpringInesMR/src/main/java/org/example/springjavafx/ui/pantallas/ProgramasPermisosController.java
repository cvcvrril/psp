package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ProgramasPermisosController {

    @FXML
    private TableView<Cosa> programasTable;
    @FXML
    private TableColumn<Cosa, UUID> idProgramasField;
    @FXML
    private TableColumn<Cosa, String> nombreProgramasField;
    @FXML
    private TableColumn<Cosa, String> contrasenaProgramasField;
    @FXML
    private TableView<Cosita> permisosTable;

    private final ServiciosCosas servicios;

    public ProgramasPermisosController(ServiciosCosas servicios) {
        this.servicios = servicios;
    }

    public void initialize(){
        programasTable.getItems().setAll(servicios.getAll(UUID.randomUUID()).getOrNull());
    }

}
