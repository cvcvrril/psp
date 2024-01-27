package org.example.springjavafx.ui.pantallas;

public enum Pantallas {

    LOGINREGISTRO("/fxml/loginregistro.fxml"),
    PROGRAMASPERMISOS("/fxml/programaspermisos.fxml"),
    PRINCIPAL("/fxml/principal.fxml");

    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta(){
        return ruta;
    }
}