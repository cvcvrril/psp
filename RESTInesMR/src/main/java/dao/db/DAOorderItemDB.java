package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.DAOorderItem;
import dao.DBConnection;
import dao.modelo.OrderItem;
import domain.modelo.excepciones.BadArgumentException;
import domain.modelo.excepciones.BaseCaidaException;
import domain.modelo.excepciones.WrongObjectException;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderItemDB implements DAOorderItem {

    private final DBConnection db;

    @Inject
    public DAOorderItemDB(Configuration configuration, DBConnection db) {
        this.db = db;
    }

    public Either<ApiError, List<OrderItem>> getAll() {
        List<OrderItem> orderItemList = new ArrayList<>();
        Either<ApiError, List<OrderItem>> res;
        try (Connection myconnection = db.getConnection()) {
            Statement stmt = myconnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_ORDER_ITEMS);
            orderItemList = readRS(rs);
            res = Either.right(orderItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, OrderItem> get(int id) {
        Either<ApiError, OrderItem> res;
        try (Connection myconnection = db.getConnection()) {
            PreparedStatement pstmt = myconnection.prepareStatement(SQLqueries.SELECT_FROM_ORDER_ITEMS_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<OrderItem> orderItemList = readRS(rs);
            if (!orderItemList.isEmpty()) {
                res = Either.right(orderItemList.get(0));
            } else {
                throw new WrongObjectException(ConstantsDAO.WRONG_OBJECT_EXCEPTION);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, List<OrderItem>> getByOrderId(int orderId) {
        List<OrderItem> orderItemList = new ArrayList<>();
        Either<ApiError, List<OrderItem>> res;
        try (Connection myconnection = db.getConnection()) {
            PreparedStatement pstmt = myconnection.prepareStatement(SQLqueries.SELECT_FROM_ORDER_ITEMS_WHERE_ORDER_ID);
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            orderItemList = readRS(rs);
            res = Either.right(orderItemList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, Integer> update(OrderItem orderItem) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.UPDATE_ORDER_ITEMS);
            pstmt.setInt(1, orderItem.getId());
            pstmt.setInt(2, orderItem.getOrderId());
            pstmt.setInt(3, orderItem.getMenuItem());
            pstmt.setInt(4, orderItem.getQuantity());
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
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
            orderItemList.add(new OrderItem(id, orderId, menuItemId, quantity));
        }
        return orderItemList;
    }
}
