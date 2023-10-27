package dao.db;

import common.Configuration;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class DAOorderItemDB {

    //TODO: hacer el getAll() y el get(i)

    private final Configuration configuration;
    private final DBConnection db;

    public DAOorderItemDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
    }

}
