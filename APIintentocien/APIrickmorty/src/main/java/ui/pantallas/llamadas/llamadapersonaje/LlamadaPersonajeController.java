package ui.pantallas.llamadas.llamadapersonaje;

import domain.usecase.LoadPersonajeUsecase;
import jakarta.inject.Inject;
import ui.pantallas.common.BasePantallaController;

public class LlamadaPersonajeController extends BasePantallaController {

    /*Atributos*/

    private final LoadPersonajeUsecase loadPersonajeUsecase;

    /*Constructores*/
    @Inject
    public LlamadaPersonajeController(LoadPersonajeUsecase loadPersonajeUsecase) {
        this.loadPersonajeUsecase = loadPersonajeUsecase;
    }

    /*Métodos*/

    public void initialize() {


    }

}
