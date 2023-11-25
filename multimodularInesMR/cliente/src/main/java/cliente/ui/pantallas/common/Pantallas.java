package cliente.ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/principal.fxml"),      //base de todas las pantallas, no tocar
    LOGIN("/fxml/login.fxml"),
    WELCOME("/fxml/welcome.fxml"),

    /*LLAMADAS*/
    LISTA_PERSONAJE("/fxml/personaje/lista_personaje.fxml"),
    ADD_PERSONAJE("/fxml/personaje/add_personaje.fxml"),
    LISTA_RAZA("/fxml/raza/lista_raza.fxml"),
    LISTA_FACCION("/fxml/faccion/lista_faccion.fxml");


    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
