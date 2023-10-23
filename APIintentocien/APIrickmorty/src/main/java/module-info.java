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
    requires com.squareup.moshi;

    /*Pantallas*/

    exports ui.pantallas.common;
    exports ui.pantallas.principal;
    exports ui.pantallas.llamadas.llamadaepisodio;
    exports ui.pantallas.llamadas.llamadapersonaje;
    exports ui.pantallas.welcome;
    exports ui.pantallas.llamadas.llamadalugar;
    exports domain.usecase;
    exports domain;
    exports dao.impl;

    /*Otros exports*/

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
    opens domain.modelo to moshi;
    exports domain.modelo;
    exports dao.retrofit.modelo.episodios;
    opens dao.retrofit.modelo.episodios to moshi;
    exports dao.retrofit.modelo.lugares;
    opens dao.retrofit.modelo.lugares to moshi;
    exports dao.retrofit.modelo.personajes;
    opens dao.retrofit.modelo.personajes to moshi;
}