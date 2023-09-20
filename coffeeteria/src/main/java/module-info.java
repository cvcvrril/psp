module coffeeteria {
    requires javafx.graphics;
    requires jakarta.jakartaee.api;
    requires javafx.fxml;
    requires io.vavr;
    requires lombok;
    requires org.apache.logging.log4j;
    requires javafx.controls;

    exports ui.main to javafx.graphics;
}