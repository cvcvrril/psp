package dao.db;

import common.Configuration;
import dao.ConstantsDAO;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.TableRestaurant;
import model.errors.ErrorCTables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
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
        try (Connection myconnection = db.getConnection()){
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from restaurant_tables");
            tableRestaurantList = readRS(rs);
            res = Either.right(tableRestaurantList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCTables(e.getMessage(),  0));
        }
        return res;
    }

    private List<TableRestaurant> readRS(ResultSet rs) throws SQLException {
        List<TableRestaurant> tableRestaurantList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt(ConstantsDAO.TABLE_NUMBER_ID);
            int seats = rs.getInt(ConstantsDAO.NUMBER_OF_SEATS);
            tableRestaurantList.add(new TableRestaurant(id, seats));
        }
        return tableRestaurantList;
    }
}
