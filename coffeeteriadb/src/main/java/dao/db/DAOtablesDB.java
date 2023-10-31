package dao.db;

import common.Configuration;
import io.vavr.control.Either;
import model.TableRestaurant;
import model.errors.ErrorCTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOtablesDB {

    //TODO: hacer el getAll() y el get(i)

    private final Configuration configuration;
    private final DBConnection db;

    public DAOtablesDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
    }

    private Either<ErrorCTables, List<TableRestaurant>> getAll(){
        List<TableRestaurant> tableRestaurantList = new ArrayList<>();
        Either <ErrorCTables, List<TableRestaurant>> res;
        try (Connection myconnection = ){

        }
    }

    private List<TableRestaurant> readRS(ResultSet rs) throws SQLException {
        List<TableRestaurant> tableRestaurantList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("table_number_id");
            int seats = rs.getInt("number_of_seats");
            tableRestaurantList.add(new TableRestaurant(id, seats));
        }
        return tableRestaurantList;
    }
}
