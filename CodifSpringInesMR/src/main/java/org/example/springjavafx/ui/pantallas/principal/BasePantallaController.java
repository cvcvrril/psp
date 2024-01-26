package org.example.springjavafx.ui.pantallas.principal;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public abstract class BasePantallaController {

    private PrincipalController pirncipalController;

    public void setPirncipalController(PrincipalController pirncipalController) {
        this.pirncipalController = pirncipalController;
    }

    abstract public void principalCargado();

}
