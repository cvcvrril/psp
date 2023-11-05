package dao.spring;

import dao.connection.DBConnectionPool;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.errors.ErrorCCustomer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class DAOcustomerSpring {

    private final DBConnectionPool pool;

    @Inject
    public DAOcustomerSpring(DBConnectionPool pool) {
        this.pool = pool;
    }

    //TODO: ????????????????????????? POR QUÉ ME PILLAS LAS COSAS A 0 MENOS EL FIRSTNAME ???????????????????????????????????????????????????????????????????????

    public Either<ErrorCCustomer,List<Customer>> getAll() throws SQLException {
        Either<ErrorCCustomer, List<Customer>> res;
        JdbcTemplate jtm =new JdbcTemplate(pool.getDataSource());
        List customerList =jtm.query("select * from customers", BeanPropertyRowMapper.newInstance(Customer.class));
        res = Either.right(customerList);
        return res;
    }
}
