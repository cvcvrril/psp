package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/principal.fxml"),      //base de todas las pantallas, no tocar
    LOGIN ("/fxml/login.fxml"),
    WELCOME("/fxml/welcome.fxml"),
    CUS_LIST ("/fxml/customer_list.fxml"),
    CUS_ADD ("/fxml/update_customer.fxml"),
    CUS_UPDATE("/fxml/update_customer.fxml"),
    CUS_DEL("/fxml/del_customer.fxml"),
    PANTALLANUEVA (ConstantesPantallas.FXML_PANTALLA_NUEVA_FXML);

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
