package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.springjavafx.common.Constantes;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.servicios.ServiciosClaves;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
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

    @FXML
    private Label contrasenaText;

    private final ServiciosCosas serviciosCosas;
    private final ServiciosUsuarios serviciosUsuarios;
    private final ServiciosClaves serviciosClaves;

    public ProgramasPermisosController(ServiciosCosas serviciosCosas, ServiciosUsuarios serviciosUsuarios, ServiciosClaves serviciosClaves) {
        this.serviciosCosas = serviciosCosas;
        this.serviciosUsuarios = serviciosUsuarios;
        this.serviciosClaves = serviciosClaves;
    }

    public void initialize(){

    }

    @FXML
    private void addPrograma(){
        String nombrePrograma = nombreProgramaField.getText();
        String contrasenaPrograma = contrasenaProgramaField.getText();
        if (nombrePrograma.isEmpty() || contrasenaPrograma.isEmpty()){
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        }else {
            Cosa cosaAdd = new Cosa(UUID.randomUUID(), nombrePrograma, contrasenaPrograma, getPrincipalController().getUser(), new ArrayList<>());
            if (serviciosCosas.add(cosaAdd).isRight()){
                getPrincipalController().sacarAlertConf(Constantes.PROGRAMA_AGREGADO_CORRECTAMENTE);
                nombreProgramaField.clear();
                contrasenaProgramaField.clear();
                cargarTabla();
            }
        }
    }

    private void cargarTabla(){
        programasTable.getItems().setAll(serviciosCosas.getAll(getPrincipalController().getUser().getId()).get());
        idProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
        nombreProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        contrasenaProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.CONTRASENA));
    }

    @FXML
    private void mostrarContrasenaPrograma(){
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado == null){
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_PROGRAMA_DE_LA_LISTA);
        }else {
            String contrasenaDesencriptada = serviciosClaves.decryptCode(programaSeleccionado.getContrasena());
            contrasenaText.setText(contrasenaDesencriptada);
        }
    }

    @Override
    public void principalCargado() {
        cargarTabla();
    }

}
