package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/customer_list.fxml"),
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
