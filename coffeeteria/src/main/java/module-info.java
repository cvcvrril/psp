module coffeeteria {
    requires javafx.graphics;
    requires javafx.fxml;
    requires io.vavr;
    requires lombok;
    requires org.apache.logging.log4j;
    requires javafx.controls;
    requires jakarta.cdi;

    exports common;
    exports services;
    exports model;

    exports ui.main;
    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.customerlist;
    exports ui.pantallas.orderupdate;
    exports ui.pantallas.orderlist;

    exports dao.imp;

    opens ui.main;
    //opens ui.pantallas.login;
    opens ui.pantallas.customerlist;
}