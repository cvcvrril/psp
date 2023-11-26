module cliente {

    requires lombok;
    requires org.apache.logging.log4j;

    requires java.naming;
    requires java.logging;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires domain;
    requires com.google.gson;
    requires okhttp3;
    requires retrofit2.converter.gson;
    requires retrofit2;
    requires retrofit2.adapter.rxjava3;
    requires io.reactivex.rxjava3;
    requires io.vavr;
    requires jakarta.cdi;
    requires jakarta.inject;


    exports cliente.data;
    exports cliente.domain.usecases;
    exports cliente.ui.main to javafx.graphics;
    exports cliente.ui.pantallas.personaje;
    exports cliente.ui.pantallas.principal;
    exports cliente.ui.pantallas.common;
    exports cliente.ui.pantallas.welcome;
    exports cliente.ui.pantallas.raza;
    exports cliente.ui.pantallas.faccion;
    exports cliente.ui.pantallas.login;


    exports cliente.data.network;

    opens cliente.ui.main;

    opens cliente.ui.pantallas.principal;

    opens cliente.ui.pantallas.personaje to javafx.fxml;
    opens cliente.ui.pantallas.raza to javafx.fxml;
    opens cliente.ui.pantallas.welcome to javafx.fxml;
    opens cliente.ui.pantallas.faccion to javafx.fxml;
    opens cliente.ui.pantallas.login to javafx.fxml;

}
