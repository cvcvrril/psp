package dao.spring;

import dao.connection.DBConnectionPool;
import dao.mappers.CustomerMapper;
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

    //TODO: hacer el resto de métodos

    public Either<ErrorCCustomer,List<Customer>> getAll() throws SQLException {
        Either<ErrorCCustomer, List<Customer>> res;
        JdbcTemplate jtm =new JdbcTemplate(pool.getDataSource());
        List<Customer> customerList =jtm.query("select * from customers", new CustomerMapper());
        res = Either.right(customerList);
        return res;
    }

    public Either<ErrorCCustomer, Customer> get (int id){
        Either<ErrorCCustomer, Customer> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<Customer> cus = jtm.query("select * from customers where id =?", new CustomerMapper(), id);
        if (cus.isEmpty()){
            res = Either.left(new ErrorCCustomer("vacio", 0));
        } else {
            res = Either.right(cus.get(0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Integer> add(Customer newCustomer){
        Either<ErrorCCustomer, Customer> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        try {
            int rowsAffected = jtm.update("INSERT INTO customers (first_name, last_name, email, phone, date_of_birth) VALUES (?, ?, ?, ?, ?)",
                    newCustomer.getFirstName(), newCustomer.getSecondName(), newCustomer.getEmailCus(), newCustomer.getPhoneNumber(), newCustomer.getDateBirth());

            if (rowsAffected > 0) {
                res = Either.right(newCustomer);
            } else {
                res = Either.left(new ErrorCCustomer("Error al agregar el cliente", 500)); // Personaliza el mensaje y el código de error según tus necesidades.
            }
        } finally {

        }
        return res;
    }
}
