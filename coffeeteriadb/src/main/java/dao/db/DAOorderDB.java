package dao.db;

import common.Configuration;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

//TODO: hacer todos los métodos

@Log4j2
public class DAOorderDB {

    /*Atb*/

    private final Configuration config;
    private final DBConnection db;



    /*Cons*/
    @Inject
    public DAOorderDB(Configuration config, DBConnection db) {
        this.config = config;
        this.db = db;
    }

    /*Methods*/


    /*getAll()*/

}
