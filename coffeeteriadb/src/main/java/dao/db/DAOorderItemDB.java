package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.connection.DBConnection;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.OrderItem;
import model.errors.ErrorCOrderItem;
import services.SERVmenuItems;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderItemDB {

    private final Configuration configuration;
    private final DBConnection db;
    private final SERVmenuItems serVmenuItems;

    @Inject
    public DAOorderItemDB(Configuration configuration, DBConnection db, SERVmenuItems serVmenuItems) {
        this.configuration = configuration;
        this.db = db;
        this.serVmenuItems = serVmenuItems;
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getAll() {
        List<OrderItem> orderItemList;
        Either<ErrorCOrderItem, List<OrderItem>> res;
        try (Connection myconnection = db.getConnection()) {
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_ORDER_ITEMS);
            orderItemList = readRS(rs);
            res = Either.right(orderItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrderItem, OrderItem> get(int id) {
        Either<ErrorCOrderItem, OrderItem> res;
        try (Connection myconnection = db.getConnection()) {
            PreparedStatement pstmt = myconnection.prepareStatement(SQLqueries.SELECT_FROM_ORDER_ITEMS_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<OrderItem> orderItemList = readRS(rs);
            if (!orderItemList.isEmpty()) {
                res = Either.right(orderItemList.get(0));
            } else {
                res = Either.left(new ErrorCOrderItem(ConstantsDAO.ERROR_READING_DATABASE, 0));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrderItem, Integer> update(OrderItem orderItem) {
        int rowsAffected;
        Either<ErrorCOrderItem, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.UPDATE_ORDER_ITEMS);
            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getMenuItem());
            pstmt.setInt(3, orderItem.getQuantity());
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrderItem, Integer> delete(int id) {
        Either<ErrorCOrderItem, Integer> res;
        int rowsAffected;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.DELETE_ORDER_ITEMS);
            pstmt.setInt(1, id);
            rowsAffected = pstmt.executeUpdate();
            if (rowsAffected != 1) {
                res = Either.left(new ErrorCOrderItem("There was an error at deleting the order item", 0));
            } else {
                res = Either.right(rowsAffected);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrderItem, Integer> add(List<OrderItem> orderItemList, int id) {
        Either<ErrorCOrderItem, Integer> res = null;
        int rowsAffected;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.INSERT_ORDER_ITEM_GEN, Statement.RETURN_GENERATED_KEYS);
            for (OrderItem orderItem: orderItemList){
                pstmt.setInt(1, id);
                pstmt.setInt(2,orderItem.getMenuItem());
                pstmt.setInt(3,orderItem.getQuantity());
                rowsAffected = pstmt.executeUpdate();
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()){
                    rs.getInt(1);
                }
                if (rowsAffected!=1){
                    res = Either.left(new ErrorCOrderItem("There was an error at adding the order item", 0));
                } else {
                    res = Either.right(rowsAffected);
                }
            }
        } catch (SQLException e) {
            log.error(e.getMessage(),e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(),0));
        }
        return res;
    }

    private List<OrderItem> readRS(ResultSet rs) throws SQLException {
        List<OrderItem> orderItemList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(ConstantsDAO.ORDER_ITEM_ID);
            int orderId = rs.getInt(ConstantsDAO.ORDER_ID);
            int menuItemId = rs.getInt(ConstantsDAO.MENU_ITEM_ID);
            int quantity = rs.getInt(ConstantsDAO.QUANTITY);
            orderItemList.add(new OrderItem(id, orderId, menuItemId, quantity, serVmenuItems.get(id).get()));
        }
        return orderItemList;
    }
}
