package dao.spring;

import common.SQLqueries;
import dao.ConstantsDAO;
import dao.DAOcustomer;
import dao.connection.DBConnectionPool;
import dao.mappers.CustomerMapper;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Credential;
import model.Customer;
import model.errors.ErrorCCustomer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class DAOcustomerSpring implements DAOcustomer {

    private final DBConnectionPool pool;

    @Inject
    public DAOcustomerSpring(DBConnectionPool pool) {
        this.pool = pool;
    }

    public Either<ErrorCCustomer, List<Customer>> getAll() {
        Either<ErrorCCustomer, List<Customer>> res;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            List<Customer> customerList = jtm.query(SQLqueries.SELECT_FROM_CUSTOMERS, new CustomerMapper());
            res = Either.right(customerList);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Customer> get(int id) {
        Either<ErrorCCustomer, Customer> res;
        JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
        List<Customer> cus = jtm.query(SQLqueries.SELECT_CUSTOMERS_ID, new CustomerMapper(), id);
        if (cus.isEmpty()) {
            res = Either.left(new ErrorCCustomer("vacio", 0));
        } else {
            res = Either.right(cus.get(0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Integer> add(Customer newCustomer, Credential newCredential) {
        try {
            SimpleJdbcInsert customerInsert = new SimpleJdbcInsert(pool.getDataSource()).withTableName("customers");
            Map<String, Object> customerParams = new HashMap<>();
            customerParams.put(ConstantsDAO.ID, newCustomer.getIdC());
            customerParams.put(ConstantsDAO.FIRST_NAME, newCustomer.getFirstName());
            customerParams.put(ConstantsDAO.LAST_NAME, newCustomer.getSecondName());
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

    public Either<ErrorCCustomer, Integer> delete(int id, boolean conf) {
        Either<ErrorCCustomer, Integer> res;
        TransactionDefinition txDef = new DefaultTransactionDefinition();
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(pool.getDataSource());
        TransactionStatus txStatus = transactionManager.getTransaction(txDef);
        try {
            JdbcTemplate jtm = new JdbcTemplate(transactionManager.getDataSource());

            int credentialId = jtm.queryForObject(SQLqueries.SELECT_CREDENTIAL_ID_FROM_CUSTOMERS_WHERE_ID, Integer.class, id);

            int rowsAffected = jtm.update(SQLqueries.DELETE_CUSTOMERS, id);

            if (rowsAffected == 1) {
                jtm.update(SQLqueries.DELETE_CREDENTIALS, credentialId);
                transactionManager.commit(txStatus);
                res = Either.right(id);
            } else {
                transactionManager.rollback(txStatus);
                res = Either.left(new ErrorCCustomer("Error al eliminar el cliente", 0));
            }
        } catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(),0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Integer> update(Customer updatedCustomer){
        Either<ErrorCCustomer, Integer> res;
        try {
            JdbcTemplate jtm = new JdbcTemplate(pool.getDataSource());
            res = Either.right(jtm.update(SQLqueries.UPDATE_CUSTOMERS,
                    updatedCustomer.getFirstName(),
                    updatedCustomer.getSecondName(),
                    updatedCustomer.getEmailCus(),
                    updatedCustomer.getPhoneNumber(),
                    updatedCustomer.getDateBirth(),
                    updatedCustomer.getIdC()));
        }catch (Exception e){
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(),0));
        }
        return res;
    }
}