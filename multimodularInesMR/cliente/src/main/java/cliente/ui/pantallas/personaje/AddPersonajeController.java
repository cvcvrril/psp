package cliente.ui.pantallas.personaje;

import cliente.domain.usecases.AddPersonajeUseCase;
import cliente.ui.pantallas.common.BasePantallaController;
import domain.modelo.Personaje;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class AddPersonajeController extends BasePantallaController {

    private AddPersonajeViewModel viewModel;
    private final AddPersonajeUseCase useCase;


    @FXML
    private TextField nombreField;
    @FXML
    private TextField planetaResidenciaField;
    @FXML
    private TextField razaIdField;

    @Inject
    public AddPersonajeController(AddPersonajeViewModel viewModel, AddPersonajeUseCase useCase) {
        this.viewModel = viewModel;
        this.useCase = useCase;
    }

    @Override
    public void principalCargado(){
        viewModel = new AddPersonajeViewModel(useCase);
    }


    @FXML
    private void add() {
        String nombre = nombreField.getText();
        String planetaResidencia = planetaResidenciaField.getText();
        String razaId = razaIdField.getText();
        Integer razaIdInt = Integer.parseInt(razaId);

        Personaje personaje = new Personaje(0, nombre, razaIdInt, planetaResidencia, new ArrayList<>());
        viewModel.addPersonaje(personaje);
    }


}
