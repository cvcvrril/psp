package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.DBConnection;
import dao.modelo.TableRestaurant;
import domain.modelo.excepciones.BaseCaidaException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOtablesDB {

    private final Configuration configuration;
    private final DBConnection db;

    @Inject
    public DAOtablesDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
    }

    public Either<ApiError, List<TableRestaurant>> getAll(){
        List<TableRestaurant> tableRestaurantList = new ArrayList<>();
        Either <ApiError, List<TableRestaurant>> res;
        try (Connection myconnection = db.getConnection()){
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_RESTAURANT_TABLES);
            tableRestaurantList = readRS(rs);
            res = Either.right(tableRestaurantList);
        } catch (SQLException e) {
            throw new BaseCaidaException("Error al interactuar con la base de datos");
        }
        return res;
    }

    public Either<ApiError, TableRestaurant> get(int i){
        Either<ApiError, TableRestaurant> res;
        try (Connection myconnection = db.getConnection()){
            PreparedStatement pstmt = myconnection.prepareStatement(SQLqueries.SELECT_NUMBER_ID);
            pstmt.setInt(1, i);
            ResultSet rs = pstmt.executeQuery();
            List<TableRestaurant> tableRestaurantList = readRS(rs);
            if (!tableRestaurantList.isEmpty()){
                res = Either.right(tableRestaurantList.get(0));
            }else {
                //res = Either.left(new ApiError("Error al interactuar con la base de datos", LocalDateTime.now()));
                throw new BaseCaidaException("Error al interactuar con la base de datos");
            }
            rs.close();
        } catch (SQLException e) {
            throw new BaseCaidaException("Error al interactuar con la base de datos");
        }
        return res;
    }

    private List<TableRestaurant> readRS(ResultSet rs){
        try {
            List<TableRestaurant> tableRestaurantList = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt(ConstantsDAO.TABLE_NUMBER_ID);
                int seats = rs.getInt(ConstantsDAO.NUMBER_OF_SEATS);
                tableRestaurantList.add(new TableRestaurant(id, seats));
            }
            return tableRestaurantList;
        }catch (SQLException e){
            throw new BaseCaidaException("Error al leer la base de datos");
        }
    }
}
