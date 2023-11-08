package dao.spring;

import common.SQLqueries;
import dao.connection.DBConnectionPool;
import dao.mappers.OrderItemMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Order;
import model.errors.ErrorCOrder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Log4j2
public class DAOorderSpring{

    private final DBConnectionPool pool;

    @Inject
    public DAOorderSpring(DBConnectionPool pool) {
        this.pool = pool;
    }

    //TODO: revisar query

    public Either<ErrorCOrder, List<Order>> getAll(){
        Either<ErrorCOrder, List<Order>> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<Order> orderList = jtm.query(SQLqueries.SELECT_FROM_ORDERS, BeanPropertyRowMapper.newInstance(Order.class));
        if (orderList.isEmpty()){
            res = Either.left(new ErrorCOrder("error", 0));
        } else {
            res = Either.right(orderList);
        }
        return res;
    }

    public Either<ErrorCOrder, Order> get(int id){
        Either<ErrorCOrder, Order> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<Order> orderList = jtm.query(SQLqueries.SELECT_ORDERS_ID, BeanPropertyRowMapper.newInstance(Order.class));
        if (orderList.isEmpty()){
            res = Either.left(new ErrorCOrder("error", 0));
        } else {
            res = Either.right(orderList.get(0));
        }
        return res;
    }
}
