package dao.mappers;

import model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = new Order();
        order.setIdOrd(rs.getInt(""));
        order.setOrDate(rs.getDate("").toLocalDate().atStartOfDay());
        return order;
    }
}
