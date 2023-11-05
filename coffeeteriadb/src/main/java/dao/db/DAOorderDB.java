package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.connection.DBConnection;
import dao.connection.DBConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderDB {

    private final Configuration config;
    private final DBConnection db;
    private final DBConnectionPool pool;

    @Inject
    public DAOorderDB(Configuration config, DBConnection db, DBConnectionPool pool) {
        this.config = config;
        this.db = db;
        this.pool = pool;
    }

    public Either<ErrorCOrder, List<Order>> getAll(){
        List<Order> orderList = new ArrayList<>();
        Either<ErrorCOrder, List<Order>> res;
        try (Connection myConnection = pool.getConnection()){
            Statement stmt = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_ORDERS);
            orderList = readRS(rs);
            res = Either.right(orderList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrder, Order> get(int id){
        Either<ErrorCOrder, Order> res;
        try (Connection myConnection = pool.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.SELECT_ORDERS_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<Order> orderList = readRS(rs);
            if (!orderList.isEmpty()){
                res = Either.right(orderList.get(0));
            } else {
                res = Either.left(new ErrorCOrder(ConstantsDAO.ERROR_READING_DATABASE, 0));
            }
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(),  0));
        }
        return res;
    }



    public Either<ErrorCOrder, Integer> update(Order order){
        int rowsAffected;
        Either<ErrorCOrder, Integer> res;
        try (Connection myConnection = pool.getConnection()){
            try (PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.UPDATE_ORDERS)) {
                myConnection.setAutoCommit(false);
                pstmt.setTimestamp(1, Timestamp.valueOf(order.getOrDate()));
                pstmt.setInt(2, order.getIdCo());
                pstmt.setInt(3, order.getIdOrd());
                pstmt.setInt(4, order.getIdTable());
                rowsAffected = pstmt.executeUpdate();
                myConnection.commit();
                res = Either.right(rowsAffected);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrder, Integer> delete (int id){
        Either<ErrorCOrder, Integer> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt1= myConnection.prepareStatement(SQLqueries.DELETE_ORDER_ITEMS);
            pstmt1.setInt(1, id);
            pstmt1.executeUpdate();
            PreparedStatement pstmt2= myConnection.prepareStatement(SQLqueries.DELETE_ORDERS_ID);
            pstmt2.setInt(1, id);
            int rowsAffected = pstmt2.executeUpdate();
            if (rowsAffected !=1){
                res = Either.left(new ErrorCOrder(ConstantsDAO.ERROR_DELETING_ORDER, 0));
            } else {
                res = Either.right(rowsAffected);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrder, Integer> add(Order order){
        int rowsAffected;
        Either<ErrorCOrder, Integer> res;
        try (Connection myConnection = pool.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1,order.getIdOrd());
            pstmt.setTimestamp(2, Timestamp.valueOf(order.getOrDate()));
            pstmt.setInt(3, order.getIdCo());
            pstmt.setInt(4, order.getIdTable());
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected != 1) {
                res = Either.left(new ErrorCOrder(ConstantsDAO.ERROR_ADDING_ORDER, 0));
            } else {
                res = Either.right(rowsAffected);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrder(e.getMessage(), 0));
        }
        return res;
    }

    private List<Order> readRS (ResultSet rs) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt(ConstantsDAO.ORDER_ID);
            LocalDateTime dateTime = null;
            Timestamp timestamp = rs.getTimestamp(ConstantsDAO.ORDER_DATE);
            if (timestamp != null){
                dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
            int customerId = rs.getInt(ConstantsDAO.CUSTOMER_ID);
            int tableId = rs.getInt(ConstantsDAO.TABLE_ID);
            orderList.add(new Order(id, dateTime, customerId, tableId));
        }
        return orderList;
    }

}
