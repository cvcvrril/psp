package dao.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.Configuration;
import dao.ConstantsDAO;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class DBConnectionPool {

    private final Configuration config;
    private final DataSource hikariDataSource;

    @Inject
    public DBConnectionPool(Configuration config) {
        this.config = config;
        this.hikariDataSource = getHikariPool();
        try {
            Class.forName(ConstantsDAO.COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            //throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getPathSQL());
        hikariConfig.setUsername(config.getUserSQL());
        hikariConfig.setPassword(config.getPassSQL());
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty(ConstantsDAO.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(ConstantsDAO.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(ConstantsDAO.PREP_STMT_CACHE_SQL_LIMIT, 2048);

        return new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            //throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return con;
    }

    @PreDestroy
    public void closePool() {
        ((HikariDataSource) hikariDataSource).close();
    }

    public DataSource getDataSource() {
        return hikariDataSource;
    }
}
