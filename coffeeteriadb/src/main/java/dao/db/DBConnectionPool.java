package dao.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.Configuration;

import javax.sql.DataSource;

import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConnectionPool {

    private Configuration config;
    private DataSource hikariDataSource;
    private BasicDataSource basicDataSource;

    @Inject
    public DBConnectionPool(Configuration config) {
        this.config = config;
        this.hikariDataSource = getHikariPool();
        this.basicDataSource = getBasicPool();
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getPropertySQL("urlDB"));
        hikariConfig.setUsername(config.getPropertySQL("userSQL"));
        hikariConfig.setPassword(config.getPropertySQL("passSQL"));
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty("cachePrepStmts", true);
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

        return new HikariDataSource(hikariConfig);
    }

    private BasicDataSource getBasicPool(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUsername(config.getPropertySQL("userSQL"));
        basicDataSource.setPassword(config.getPropertySQL("passSQL"));
        basicDataSource.setUrl(config.getPropertySQL("pathSQL"));
        return basicDataSource;
    }

    public Connection getConnection() {
        Connection con=null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return con;
    }

    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }
}
