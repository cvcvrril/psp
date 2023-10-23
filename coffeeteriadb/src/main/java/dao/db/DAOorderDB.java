package dao.db;

import common.Configuration;
import common.SQLqueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO: hacer todos los métodos

@Log4j2
public class DAOorderDB {

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
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_ORDERS);
            orderList = readRS(rs);
            res = Either.right(orderList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return null;
    }

    public Either<ErrorCOrder, List<Order>> get(int id){
        Either<ErrorCOrder, List<Order>> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement("select * from orders where order_id=?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(),  0));
        }
        return null;
    }

    public Either<ErrorCOrder, List<Order>> delete (int id){
        Either<ErrorCOrder, List<Order>> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt= myConnection.prepareStatement("delete from orders where ");
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
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
