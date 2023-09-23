package ui.pantallas.common;

public enum Pantallas {

    /*MAIN*/
    PRINCIPAL("/fxml/principal.fxml"),      //base de todas las pantallas, no tocar
    LOGIN ("/fxml/login.fxml"),
    WELCOME("/fxml/welcome.fxml"),

    /*CUSTOMERS*/
    CUS_LIST ("/fxml/customer_list.fxml"),
    CUS_ADD ("/fxml/add_customer.fxml"),
    CUS_UPDATE("/fxml/update_customer.fxml"),
    CUS_DEL("/fxml/del_customer.fxml"),

    /*ORDERS*/
    OR_LIST("/fxml/order_list.fxml"),
    OR_ADD("/fxml/add_order.fxml"),
    OR_UPDATE("/fxml/update_order.fxml"),
    OR_DEL("/fxml/del_order.fxml");

    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
