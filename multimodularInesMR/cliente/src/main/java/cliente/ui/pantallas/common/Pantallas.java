package cliente.ui.pantallas.common;

public enum Pantallas {

    /*MAIN*/
    PRINCIPAL("/fxml/principal.fxml"),      //base de todas las pantallas, no tocar
    LOGIN("/fxml/login.fxml"),
    WELCOME("/fxml/welcome.fxml"),

    /*LLAMADAS*/
    LLA_PERSONAJE("/fxml/llamada-personaje.fxml"),
    LLA_LUGAR("/fxml/llamada-lugar.fxml"),
    LLA_EPISODIO("/fxml/llamada-episodio.fxml");


    private String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }


}
