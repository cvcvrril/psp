module coffeeteria {

    /*Se requieren*/

    requires javafx.graphics;
    requires javafx.fxml;
    requires io.vavr;
    requires lombok;
    requires org.apache.logging.log4j;
    requires javafx.controls;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires jakarta.xml.bind;
    requires org.glassfish.jaxb.runtime;
    requires MaterialFX;
    requires io.reactivex.rxjava3;
    requires org.pdfsam.rxjavafx;
    requires java.logging;
    requires java.sql;
    requires commons.dbcp2;
    requires com.zaxxer.hikari;

    /*Pantallas*/

    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.principal;
    exports ui.pantallas.orderupdate;
    exports ui.pantallas.orderlist;
    exports ui.pantallas.orderdelete;
    exports ui.pantallas.customerupdate;
    exports ui.pantallas.customerdelete;
    exports ui.pantallas.customerlist;
    exports ui.pantallas.welcome;
    exports ui.pantallas.customeradd;
    exports ui.pantallas.orderadd;

    /*Otros exports*/

    exports model.errors;
    exports dao.imp;
    exports common;
    exports services;
    exports model;
    exports ui.main to javafx.graphics;
    exports dao.xml;
    exports dao;
    exports dao.db;

    /*Abrir módulos*/

    opens fxml;
    opens config;
    opens ui.main;
    opens services;
    opens common;
    opens ui.pantallas.login;
    opens ui.pantallas.principal;
    opens ui.pantallas.welcome;
    opens ui.pantallas.orderdelete to javafx.fxml;
    opens ui.pantallas.orderlist to javafx.fxml;
    opens ui.pantallas.customerupdate to javafx.fxml;
    opens ui.pantallas.customerlist to javafx.fxml;
    opens ui.pantallas.orderupdate to javafx.fxml;
    opens ui.pantallas.customeradd to javafx.fxml;
    opens ui.pantallas.customerdelete to javafx.fxml;
    opens ui.pantallas.orderadd to javafx.fxml;
    opens model to jakarta.xml.bind;
    exports model.xml;
    opens model.xml to jakarta.xml.bind;
}