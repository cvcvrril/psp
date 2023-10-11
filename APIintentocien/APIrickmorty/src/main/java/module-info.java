module APIrickmorty {

    /*Se requieren*/

    requires javafx.graphics;
    requires javafx.fxml;
    requires io.vavr;
    requires lombok;
    requires org.apache.logging.log4j;
    requires javafx.controls;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires okhttp3;
    requires retrofit2.converter.moshi;
    requires retrofit2;
    requires retrofit2.adapter.rxjava3;
    requires moshi;

    /*Pantallas*/

    exports ui.pantallas.common;
    exports ui.pantallas.principal;
    exports ui.pantallas.llamadas.llamadaepisodio;
    exports ui.pantallas.llamadas.llamadapersonaje;
    exports ui.pantallas.welcome;
    exports ui.pantallas.llamadas.llamadalugar;
    exports dao.retrofit.modelo;
    exports domain.usecase;
    exports domain;
    exports dao.impl;

    /*Otros exports*/

    exports domain.errors;
    exports common;
    exports ui.main to javafx.graphics;

    /*Abrir módulos*/

    opens fxml;
    opens ui.main;
    opens ui.pantallas.principal;
    opens ui.pantallas.welcome;
    opens config;
    opens ui.pantallas.llamadas.llamadaepisodio to javafx.fxml;
    opens ui.pantallas.llamadas.llamadapersonaje to javafx.fxml;
    opens ui.pantallas.llamadas.llamadalugar to javafx.fxml;
    opens dao.retrofit.modelo to moshi;
    opens domain.modelo to moshi;
    exports domain.modelo;
}