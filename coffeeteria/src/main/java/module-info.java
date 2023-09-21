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
    exports ui.pantallas.principal;
    exports ui.pantallas.orderupdate;
    exports ui.pantallas.orderlist;
    exports ui.pantallas.orderdelete;
    exports ui.pantallas.customerupdate;
    exports ui.pantallas.customerdelete;

    exports dao.imp;

    opens ui.main;
    opens ui.pantallas.login;
    //opens ui.pantallas.principal;
}