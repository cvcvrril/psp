package dao.spring;

import dao.connection.DBConnectionPool;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DAOorderItemSpring {

    private final DBConnectionPool pool;

    @Inject
    public DAOorderItemSpring(DBConnectionPool pool) {
        this.pool = pool;
    }



}
