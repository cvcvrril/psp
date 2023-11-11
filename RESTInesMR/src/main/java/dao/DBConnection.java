package dao;

import common.Configuration;
import domain.modelo.excepciones.BaseCaidaException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private final Configuration config;

    @Inject
    public DBConnection(Configuration config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName(ConstantsDAO.COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return DriverManager
                .getConnection(config.getPathSQL(), config.getUserSQL(), config.getPassSQL());
    }

}
