package org.example.springjavafx.ui.pantallas;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.springjavafx.common.Constantes;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.servicios.ServiciosClaves;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.example.springjavafx.servicios.ServiciosCositas;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.UUID;

@Component
public class ProgramasPermisosController extends BasePantallaController {



    @FXML
    private PasswordField contrasenaProgramaField;
    @FXML
    private TableView<Cosa> programasTable;
    @FXML
    private TableColumn<Cosa, String> nombreUserProgramasColumn;
    @FXML
    private TableColumn<Cosa, String> nombreProgramasColumn;
    @FXML
    private TableColumn<Cosa, String> contrasenaProgramasColumn;
    @FXML
    private TableColumn<Cosa, String> firmaProgramaColumn;
    @FXML
    private TextField nombreProgramaField;
    @FXML
    private TextField newContrasenaField;


    @FXML
    private TableView<Cosita> permisosTable;
    @FXML
    private TableColumn<Cosita, UUID> idPermisoColumn;
    @FXML
    private TableColumn<Cosita, String> nombreUserPermisoColumn;
    @FXML
    private TableColumn<Cosita, String> asymPermisoColumn;

    @FXML
    private Label contrasenaText;

    private final ServiciosCosas serviciosCosas;
    private final ServiciosUsuarios serviciosUsuarios;
    private final ServiciosClaves serviciosClaves;
    private final ServiciosCositas serviciosCositas;

    public ProgramasPermisosController(ServiciosCosas serviciosCosas, ServiciosUsuarios serviciosUsuarios, ServiciosClaves serviciosClaves, ServiciosCositas serviciosCositas) {
        this.serviciosCosas = serviciosCosas;
        this.serviciosUsuarios = serviciosUsuarios;
        this.serviciosClaves = serviciosClaves;
        this.serviciosCositas = serviciosCositas;
    }

    public void initialize() {

    }

    private void cargarTablaProgramas() {
        //programasTable.getItems().setAll(serviciosCosas.getAll(getPrincipalController().getUser().getId()).get());
        programasTable.getItems().setAll(serviciosCosas.getALl().get());
        nombreProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        contrasenaProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.CONTRASENA));
        nombreUserProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.USERNAME));
    }

    private void cargarTablaPermisos(MouseEvent event){
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado != null){
            permisosTable.getItems().setAll(serviciosCositas.getAllByProgramId(programaSeleccionado.getId()).get());
            idPermisoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
            nombreUserPermisoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.USERNAME));
            asymPermisoColumn.setCellValueFactory(new PropertyValueFactory<>("asym"));
        }
    }

    @FXML
    private void addPrograma() {
        String nombrePrograma = nombreProgramaField.getText();
        String contrasenaPrograma = contrasenaProgramaField.getText();
        if (nombrePrograma.isEmpty() || contrasenaPrograma.isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        } else {
            Cosa programaAdd = new Cosa(UUID.randomUUID(), nombrePrograma, contrasenaPrograma, getPrincipalController().getUser().getName(), getPrincipalController().getUser(), new ArrayList<>());
            Cosita permisoAdd = new Cosita(UUID.randomUUID(),getPrincipalController().getUser().getName(),null,programaAdd);
            if (serviciosCosas.add(programaAdd, permisoAdd).isRight()) {
                getPrincipalController().sacarAlertConf(Constantes.PROGRAMA_AGREGADO_CORRECTAMENTE);
                nombreProgramaField.clear();
                contrasenaProgramaField.clear();
                cargarTablaProgramas();
            }
        }
    }

    @FXML
    private void mostrarContrasenaPrograma() {
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado == null) {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_PROGRAMA_DE_LA_LISTA);
        } else {
            String contrasenaDesencriptada = serviciosClaves.decryptCode(programaSeleccionado.getContrasena());
            contrasenaText.setText(contrasenaDesencriptada);
        }
    }

    @FXML
    private void changePassword() {
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        String newContrasena = newContrasenaField.getText();
        if (newContrasena == null || programaSeleccionado == null) {
            getPrincipalController().sacarAlertError(Constantes.EL_CAMPO_ESTA_SIN_COMPLETAR);
        } else {
            Cosa programaContrasenaCambiada = new Cosa(programaSeleccionado.getId(), programaSeleccionado.getNombre(), newContrasena, programaSeleccionado.getUsername(), programaSeleccionado.getUser(), programaSeleccionado.getCositas());
            if (serviciosCosas.changePassword(programaContrasenaCambiada).isRight()){
                getPrincipalController().sacarAlertConf(Constantes.CONTRASENA_CAMBIADA_CORRECTAMENTE);
                newContrasenaField.clear();
                cargarTablaProgramas();
            }
        }
    }

    @Override
    public void principalCargado() {
        getPrincipalController().menuAccount.setVisible(true);
        cargarTablaProgramas();
        programasTable.setOnMouseClicked(this::cargarTablaPermisos);
    }

}
