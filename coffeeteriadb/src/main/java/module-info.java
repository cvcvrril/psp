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
    requires spring.jdbc;
    requires spring.tx;

    /*Pantallas*/

    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.principal;
    exports ui.pantallas.order.orderupdate;
    exports ui.pantallas.order.orderlist;
    exports ui.pantallas.order.orderdelete;
    exports ui.pantallas.customer.customerupdate;
    exports ui.pantallas.customer.customerdelete;
    exports ui.pantallas.customer.customerlist;
    exports ui.pantallas.welcome;
    exports ui.pantallas.customer.customeradd;
    exports ui.pantallas.order.orderadd;

    /*Otros exports*/

    exports model.errors;
    exports dao.imp;
    exports common;
    exports services;
    exports model;
    exports ui.main to javafx.graphics;
    exports dao;
    exports dao.db;
    exports dao.mappers;

    /*Abrir módulos*/

    opens fxml;
    opens config;
    opens ui.main;
    opens services;
    opens common;
    opens ui.pantallas.login;
    opens ui.pantallas.principal;
    opens ui.pantallas.welcome;
    opens ui.pantallas.order.orderdelete to javafx.fxml;
    opens ui.pantallas.order.orderlist to javafx.fxml;
    opens ui.pantallas.customer.customerupdate to javafx.fxml;
    opens ui.pantallas.customer.customerlist to javafx.fxml;
    opens ui.pantallas.order.orderupdate to javafx.fxml;
    opens ui.pantallas.customer.customeradd to javafx.fxml;
    opens ui.pantallas.customer.customerdelete to javafx.fxml;
    opens ui.pantallas.order.orderadd to javafx.fxml;
    opens model to jakarta.xml.bind;
    exports model.xml;
    opens model.xml to jakarta.xml.bind;
    exports dao.connection;
    exports dao.spring;
}