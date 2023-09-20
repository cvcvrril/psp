package ui.pantallas.common;

public enum Pantallas {

    PANTALLA1 ("/fxml/screen1.fxml"),
    LOGIN ("/fxml/login.fxml"),
    UPDATE_ORDER ("/fxml/update_order.fxml"),
    DETALLE ("/fxml/detalle.fxml"),
    PANTALLANUEVA (ConstantesPantallas.FXML_PANTALLA_NUEVA_FXML);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
