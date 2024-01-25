package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.springjavafx.data.modelo.Cache;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.PrincipalController;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
public class ProgramasPermisosController {

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

    private final PrincipalController principal;
    private final ServiciosCosas serviciosCosas;
    private final ServiciosUsuarios serviciosUsuarios;
    private final Cache passwordCache;
    private User usuarioLog;

    //TODO: Preguntar si lo del usuario se puede hacer de otra forma???????

    public ProgramasPermisosController(PrincipalController principal, ServiciosCosas serviciosCosas, ServiciosUsuarios serviciosUsuarios, Cache passwordCache) {
        this.principal = principal;
        this.serviciosCosas = serviciosCosas;
        this.serviciosUsuarios = serviciosUsuarios;
        this.passwordCache = passwordCache;
    }

    public void initialize(){
        usuarioLog = serviciosUsuarios.getByPassword(passwordCache.getUserPassword());
        programasTable.getItems().setAll(serviciosCosas.getAll(UUID.randomUUID()).getOrNull());
    }

    @FXML
    private void addPrograma(){
        String nombrePrograma = nombreProgramasColumn.getText();
        String contrasenaPrograma = contrasenaProgramasColumn.getText();
        if (nombrePrograma.isEmpty() || contrasenaPrograma.isEmpty()){
            principal.sacarAlertError("Hay campos vacíos.");
        }else {
            Cosa cosaAdd = new Cosa(UUID.randomUUID(), nombrePrograma, contrasenaPrograma, usuarioLog, new ArrayList<>());
            serviciosCosas.add(cosaAdd);
        }
    }

}
