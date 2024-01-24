package org.example.springjavafx.ui.pantallas;

public enum Pantallas {

    LOGINREGISTRO("/fxml/loginregistro.fxml");

    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta(){
        return ruta;
    }
}
