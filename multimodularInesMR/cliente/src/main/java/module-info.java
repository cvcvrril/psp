module cliente {

    requires lombok;

    requires java.naming;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.fxml;
    requires jakarta.jakartaee.api;
    requires javafx.controls;


    exports cliente.data;

    exports cliente.data.network;


}
