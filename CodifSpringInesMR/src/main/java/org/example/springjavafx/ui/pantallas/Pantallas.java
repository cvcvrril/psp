package org.example.springjavafx.ui.pantallas;

import org.example.springjavafx.utils.Constantes;

public enum Pantallas {

    LOGINREGISTRO(Constantes.FXML_LOGINREGISTRO_FXML),
    PROGRAMASPERMISOS(Constantes.FXML_PROGRAMASPERMISOS_FXML),
    PRINCIPAL(Constantes.FXML_PRINCIPAL_FXML);

    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta(){
        return ruta;
    }
}
