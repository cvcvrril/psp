package dao.spring;

import dao.connection.DBConnectionPool;
import dao.mappers.OrderItemMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.OrderItem;
import model.errors.ErrorCOrder;
import model.errors.ErrorCOrderItem;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Log4j2
public class DAOorderItemSpring {

    private final DBConnectionPool pool;

    @Inject
    public DAOorderItemSpring(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<ErrorCOrderItem, List<OrderItem>> getAll(){
        Either<ErrorCOrderItem, List<OrderItem>> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<OrderItem> orderItemList = jtm.query("SELECT * FROM order_items oi inner join menu_items mi on oi.menu_item_id = mi.menu_item_id", new OrderItemMapper());
        if (orderItemList.isEmpty()){
            res = Either.left(new ErrorCOrderItem("error", 0));
        }else {
            res = Either.right(orderItemList);
        }
        return res;
    }

    public Either<ErrorCOrderItem, List<OrderItem>> get (int id){
        Either<ErrorCOrderItem, List<OrderItem>> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<OrderItem> orderItemList = jtm.query("SELECT * FROM order_items oi inner join menu_items mi on oi.menu_item_id = mi.menu_item_id where oi.order_id = ?", new OrderItemMapper(), id);
        res = Either.right(orderItemList);
        return res;
    }

}
