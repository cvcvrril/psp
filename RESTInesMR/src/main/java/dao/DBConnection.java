package dao;

import common.Configuration;
import domain.modelo.excepciones.BaseCaidaException;
import jakarta.inject.Inject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private Configuration config;

    @Inject
    public DBConnection(Configuration config) {
        this.config = config;
    }

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new BaseCaidaException("Error al conectar con la base de datos");
        }
        return DriverManager
                .getConnection(config.getPathSQL(), config.getUserSQL(), config.getPassSQL());
    }

}
