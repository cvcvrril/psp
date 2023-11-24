module domain {

    exports domain.errores;
    exports domain.modelo;

    requires lombok;
    requires org.apache.logging.log4j;

    requires java.naming;
    requires java.logging;

    opens domain.modelo to com.google.gson;

}
