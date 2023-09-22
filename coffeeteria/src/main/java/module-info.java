module coffeeteria {
    requires javafx.graphics;
    requires javafx.fxml;
    requires io.vavr;
    requires lombok;
    requires org.apache.logging.log4j;
    requires javafx.controls;
    requires jakarta.cdi;
    requires jakarta.inject;
    exports common;
    exports services;
    exports model;

    exports ui.main to javafx.graphics;

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

    exports dao.imp;

    opens ui.main;

    opens ui.pantallas.login;
    opens fxml;
    opens ui.pantallas.principal;
}