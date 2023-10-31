package dao.db;

import common.Configuration;
import common.SQLqueries;
import io.vavr.control.Either;
import lombok.extern.log4j.Log4j2;
import model.OrderItem;
import model.errors.ErrorCOrderItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DAOorderItemDB {

    //TODO: hacer el getAll(), el get(i) y update()

    private final Configuration configuration;
    private final DBConnection db;

    public DAOorderItemDB(Configuration configuration, DBConnection db) {
        this.configuration = configuration;
        this.db = db;
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

    private List<OrderItem> readRS(ResultSet rs) throws SQLException {
        List<OrderItem> orderItemList = new ArrayList<>();
        while (rs.next()){
            int id = rs.getInt("order_item_id");
            int orderId = rs.getInt("order_id");
            int menuItemId = rs.getInt("menu_item_id");
            int quantity = rs.getInt("quantity");
            orderItemList.add(new OrderItem(id, orderId, menuItemId, quantity));
        }
        return orderItemList;
    }
}
