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

    /*Abrir módulos*/

    opens fxml;
    opens ui.main;
    opens ui.pantallas.login;
    opens ui.pantallas.principal;
    opens ui.pantallas.welcome;
    opens ui.pantallas.orderdelete to javafx.fxml;
    opens ui.pantallas.orderlist to javafx.fxml;
    opens ui.pantallas.customerupdate to javafx.fxml;
    opens ui.pantallas.customerlist to javafx.fxml;
    opens ui.pantallas.orderupdate to javafx.fxml;
}