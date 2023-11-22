package cliente.ui.pantallas.common;


import cliente.ui.pantallas.principal.PrincipalController;

public abstract class BasePantallaController {

    private PrincipalController principalController;

    public PrincipalController getPrincipalController() {
        return principalController;
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void principalCargado() {

    }
}
