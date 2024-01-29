package org.example.springjavafx.ui.pantallas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.example.springjavafx.data.modelo.User;
import org.example.springjavafx.utils.Constantes;
import org.example.springjavafx.data.modelo.Cosa;
import org.example.springjavafx.data.modelo.Cosita;
import org.example.springjavafx.servicios.ServiciosClaves;
import org.example.springjavafx.servicios.ServiciosCosas;
import org.example.springjavafx.servicios.ServiciosCositas;
import org.example.springjavafx.servicios.ServiciosUsuarios;
import org.example.springjavafx.ui.pantallas.principal.BasePantallaController;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private ComboBox<String> userPermisoComboBox;

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
        //Comento algo aquí para que el SonarLint deje de llorar
    }

    private void cargarTablaProgramas() {
        programasTable.getItems().setAll(serviciosCosas.getALl().get());
        nombreProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.NOMBRE));
        contrasenaProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.CONTRASENA));
        nombreUserProgramasColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.USERNAME));
        firmaProgramaColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.FIRMA));
    }

    private void cargarComboBoxUsers(){
        List<User> userList = serviciosUsuarios.getAll().get();
        for (User user : userList){
            userPermisoComboBox.getItems().add(user.getName());
        }
    }

    private void cargarTablaPermisos(MouseEvent event){
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado != null){
            permisosTable.getItems().setAll(serviciosCositas.getAllByProgramId(programaSeleccionado.getId()).get());
            idPermisoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ID));
            nombreUserPermisoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.USERNAME));
            asymPermisoColumn.setCellValueFactory(new PropertyValueFactory<>(Constantes.ASYM));
        }
    }

    @FXML
    private void addPrograma() {
        String nombrePrograma = nombreProgramaField.getText();
        String contrasenaPrograma = contrasenaProgramaField.getText();
        if (nombrePrograma.isEmpty() || contrasenaPrograma.isEmpty()) {
            getPrincipalController().sacarAlertError(Constantes.HAY_CAMPOS_VACIOS);
        } else {
            Cosa programaAdd = new Cosa(UUID.randomUUID(), nombrePrograma, contrasenaPrograma, getPrincipalController().getUser().getName(),null,getPrincipalController().getUser(), new ArrayList<>());
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
    private void addPermiso(ActionEvent actionEvent) {
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado!= null){
            Cosita nuevoPermiso = new Cosita(UUID.randomUUID(),getPrincipalController().getUser().getName(),null, programaSeleccionado);
            if (serviciosCositas.add(nuevoPermiso, programaSeleccionado.getContrasena()).isRight()){
                getPrincipalController().sacarAlertConf(Constantes.PERMISO_ANADIDO_CON_EXITO);
            }
        }else {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_PROGRAMA_DE_LA_LISTA);
        }

    }

    @FXML
    private void mostrarContrasenaPrograma() {
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        if (programaSeleccionado == null) {
            getPrincipalController().sacarAlertError(Constantes.SELECCIONA_UN_PROGRAMA_DE_LA_LISTA);
        } else {
            if (serviciosClaves.checkCode(programaSeleccionado.getFirma(), programaSeleccionado.getUsername()).isRight()){
                String contrasenaDesencriptada = serviciosClaves.decryptCode(programaSeleccionado.getContrasena());
                String usuarioFirma = programaSeleccionado.getUser().getName();
                contrasenaText.setText(contrasenaDesencriptada+" ("+usuarioFirma+")");
            }
        }
    }

    @FXML
    private void changePassword() {
        Cosa programaSeleccionado = programasTable.getSelectionModel().getSelectedItem();
        String newContrasena = newContrasenaField.getText();
        if (newContrasena == null || programaSeleccionado == null) {
            getPrincipalController().sacarAlertError(Constantes.EL_CAMPO_ESTA_SIN_COMPLETAR);
        } else {
            Cosa programaContrasenaCambiada = new Cosa(programaSeleccionado.getId(), programaSeleccionado.getNombre(), newContrasena, programaSeleccionado.getUsername(),null, programaSeleccionado.getUser(), programaSeleccionado.getCositas());
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
        cargarComboBoxUsers();
        programasTable.setOnMouseClicked(this::cargarTablaPermisos);
    }

}
