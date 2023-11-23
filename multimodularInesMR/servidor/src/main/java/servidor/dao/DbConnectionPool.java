package servidor.dao;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import servidor.common.Configuration;
import servidor.domain.modelo.excepciones.BaseCaidaException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Singleton
public class DbConnectionPool {

    private final Configuration config;
    private final DataSource hikariDataSource;

    @Inject
    public DbConnectionPool(Configuration config) {
        this.config = config;
        this.hikariDataSource = getHikariPool();
        try {
            Class.forName(ConstantsDao.COM_MYSQL_CJ_JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
        }
    }

    private DataSource getHikariPool() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.getPathSql());
        hikariConfig.setUsername(config.getUserSql());
        hikariConfig.setPassword(config.getPassSql());
        hikariConfig.setDriverClassName(config.getDriver());
        hikariConfig.setMaximumPoolSize(4);

        hikariConfig.addDataSourceProperty(ConstantsDao.CACHE_PREP_STMTS, true);
        hikariConfig.addDataSourceProperty(ConstantsDao.PREP_STMT_CACHE_SIZE, 250);
        hikariConfig.addDataSourceProperty(ConstantsDao.PREP_STMT_CACHE_SQL_LIMIT, 2048);

        return new HikariDataSource(hikariConfig);
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = hikariDataSource.getConnection();
        } catch (SQLException e) {
            throw new BaseCaidaException(ConstantsDao.BASE_CAIDA_EXCEPTION);
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
