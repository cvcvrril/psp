package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.springjavafx.data.modelo.Cache;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
import org.example.springjavafx.ui.pantallas.principal.PrincipalController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class ProgramasPermisosController extends BasePantallaController {

    @FXML
    private TextField nombreProgramaField;
    @FXML
    private PasswordField contrasenaProgramaField;
    @FXML
    private TableView<Cosa> programasTable;
    @FXML
    private TableColumn<Cosa, UUID> idProgramasColumn;
    @FXML
    private TableColumn<Cosa, String> nombreProgramasColumn;
    @FXML
    private TableColumn<Cosa, String> contrasenaProgramasColumn;


    @FXML
    private TableView<Cosita> permisosTable;
    private final ServiciosCosas serviciosCosas;
    private final ServiciosUsuarios serviciosUsuarios;

    public ProgramasPermisosController(ServiciosCosas serviciosCosas, ServiciosUsuarios serviciosUsuarios) {
        this.serviciosCosas = serviciosCosas;
        this.serviciosUsuarios = serviciosUsuarios;
    }

    public void initialize(){

    }

    @FXML
    private void addPrograma(){
        String nombrePrograma = nombreProgramaField.getText();
        String contrasenaPrograma = contrasenaProgramaField.getText();
        if (nombrePrograma.isEmpty() || contrasenaPrograma.isEmpty()){
            getPrincipalController().sacarAlertError("Hay campos vacíos.");
        }else {
            Cosa cosaAdd = new Cosa(UUID.randomUUID(), nombrePrograma, contrasenaPrograma, getPrincipalController().getUser(), new ArrayList<>());
            serviciosCosas.add(cosaAdd);
        }
    }

    @Override
    public void principalCargado() {
        programasTable.getItems().setAll(serviciosCosas.getAll(getPrincipalController().getUser().getId()).get());
        idProgramasColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreProgramasColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));

    }
}
