module cliente {

    requires lombok;
    requires org.apache.logging.log4j;

    requires java.naming;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires jakarta.cdi;
    requires domain;
    requires com.google.gson;
    requires okhttp3;
    requires retrofit2.converter.gson;
    requires retrofit2;
    requires retrofit2.adapter.rxjava3;
    requires io.reactivex.rxjava3;
    requires io.vavr;


    exports cliente.data;
    exports cliente.ui.main to javafx.graphics;
    exports cliente.ui.pantallas.personaje;
    exports cliente.ui.pantallas.principal;
    exports cliente.ui.pantallas.common;
    exports cliente.ui.pantallas.welcome;


    exports cliente.data.network;

    opens cliente.ui.main;
    opens cliente.ui.pantallas.principal;

    opens cliente.ui.pantallas.welcome to javafx.fxml;



}
