package org.example.springjavafx.ui.pantallas.principal;

import lombok.Getter;

@Getter
public abstract class BasePantallaController {

    private PrincipalController principalController;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    abstract public void principalCargado();
}
