package dao.spring;

import dao.connection.DBConnectionPool;
import dao.mappers.CustomerMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.Customer;
import model.errors.ErrorCCustomer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class DAOcustomerSpring {

    private final DBConnectionPool pool;

    @Inject
    public DAOcustomerSpring(DBConnectionPool pool) {
        this.pool = pool;
    }

    //TODO: hacer el update y el delete

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

    //TODO: ARREGLAR USANDO LO DEL ADDSUPPLIERGK

    public Either<ErrorCCustomer, Integer> add(Customer newCustomer, Credential newCredential){
        try {
            SimpleJdbcInsert customerInsert = new SimpleJdbcInsert(pool.getDataSource()).withTableName("customers");
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put("id", newCustomer.getIdC());
            customerParams.put("first_name", newCustomer.getFirstName());
            customerParams.put("last_name", newCustomer.getSecondName());
            customerParams.put("email", newCustomer.getEmailCus());
            customerParams.put("phone", newCustomer.getPhoneNumber());
            customerParams.put("date_of_birth", newCustomer.getDateBirth());

            int customerRowsAffected = customerInsert.execute(customerParams);

            SimpleJdbcInsert credentialInsert = new SimpleJdbcInsert(pool.getDataSource()).withTableName("credential").usingGeneratedKeyColumns("id");
            Map<String, Object> credentialParams = new HashMap<>();
            credentialParams.put("user_name", newCredential.getUserName());
            credentialParams.put("password", newCredential.getPassword());
            credentialParams.put("customer_id", newCustomer.getIdC());

            if (customerRowsAffected == 1) {
                return Either.right((int) credentialInsert.executeAndReturnKey(credentialParams));
            } else {
                return Either.left(new ErrorCCustomer("Error al agregar el cliente o la credencial", 0));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
    }
}
