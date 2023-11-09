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

    public Either<ErrorCOrderItem, List<OrderItem>> getAll(){
        List<OrderItem> orderItemList = new ArrayList<>();
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

    public Either<ErrorCOrderItem, OrderItem> get(int id){
        Either<ErrorCOrderItem, OrderItem> res;
        try(Connection myconnection = db.getConnection()){
            PreparedStatement pstmt = myconnection.prepareStatement("select * from order_items where order_item_id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<OrderItem> orderItemList = readRS(rs);
            if (!orderItemList.isEmpty()){
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

    public Either<ErrorCOrderItem, List<OrderItem>> getByOrderId(int orderId){
        List<OrderItem> orderItemList = new ArrayList<>();
        Either<ErrorCOrderItem, List<OrderItem>> res;
        try(Connection myconnection = db.getConnection()){
            PreparedStatement pstmt = myconnection.prepareStatement("select * from order_items where order_id = ?");
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            orderItemList = readRS(rs);
            res = Either.right(orderItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCOrderItem, Integer> update(OrderItem orderItem){
        int rowsAffected;
        Either<ErrorCOrderItem, Integer> res;
        try (Connection myConnection = db.getConnection()){
            PreparedStatement pstmt = myConnection.prepareStatement("update order_items set order_item_id=?, order_id=?, menu_item_id=?, quantity=?");
            pstmt.setInt(1, orderItem.getId());
            pstmt.setInt(2,orderItem.getOrderId());
            pstmt.setInt(3,orderItem.getMenuItem());
            pstmt.setInt(4,orderItem.getQuantity());
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCOrderItem(e.getMessage(), 0));
        }
        return res;
    }

    private List<OrderItem> readRS(ResultSet rs) throws SQLException {
        List<OrderItem> orderItemList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt(ConstantsDAO.ORDER_ITEM_ID);
            int orderId = rs.getInt(ConstantsDAO.ORDER_ID);
            int menuItemId = rs.getInt(ConstantsDAO.MENU_ITEM_ID);
            int quantity = rs.getInt(ConstantsDAO.QUANTITY);
            orderItemList.add(new OrderItem(id, orderId, menuItemId, quantity, serVmenuItems.getAll().getOrNull()));
        }
        return orderItemList;
    }
}
