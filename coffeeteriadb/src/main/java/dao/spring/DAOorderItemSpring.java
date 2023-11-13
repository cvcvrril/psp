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
        List<OrderItem> orderItemList = jtm.query("select * from order_items", new OrderItemMapper());
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
        //List<OrderItem> orderItemList = jtm.query("SELECT * FROM order_items INNER JOIN menu_items ON menu_items.menu_item_id = order_items.menu_item_id WHERE order_items.order_id = ?;", new OrderItemMapper(), id);
        List<OrderItem> orderItemList = jtm.query("select * from order_items where order_id =?", new OrderItemMapper(), id);
        if (orderItemList.isEmpty()){
            res = Either.left(new ErrorCOrderItem("error", 0));
        } else {
            res = Either.right(orderItemList);
        }
        return res;
    }

}
