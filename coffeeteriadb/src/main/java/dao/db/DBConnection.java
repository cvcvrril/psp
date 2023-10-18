package dao.db;

import common.Configuration;
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
        return DriverManager
                .getConnection(config.getPropertySQL("pathSQL"), config.getPropertySQL("userSQL"), config.getPropertySQL("passSQL"));
    }

}
