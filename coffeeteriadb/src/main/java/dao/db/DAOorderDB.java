package dao.db;

import common.Configuration;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: hacer todos los métodos

@Log4j2
public class DAOorderDB {

    /*Atb*/

    private final Configuration config;
    private final DBConnection db;

    @Inject
    public DAOorderDB(Configuration config, DBConnection db) {
        this.config = config;
        this.db = db;
    }

    public Either<ErrorCOrder, List<Order>> getAll(){
        List<Order> orderList = new ArrayList<>();
        Either<ErrorCOrder, List<Order>> res;
        try (Connection myConnection = db.getConnection()){
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from orders");
            orderList = readRS(rs);
            res = Either.right(orderList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return null;
    }


    private List<Order> readRS (ResultSet rs) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("order_id");
            LocalDateTime dateTime = null;
            Date date = rs.getDate("order_date");
            if (date != null){
                dateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
            int customerId = rs.getInt("customer_id");
            int tableId = rs.getInt("table_id");
            orderList.add(new Order(id, dateTime, customerId, tableId));
        }
        return orderList;
    }

}
