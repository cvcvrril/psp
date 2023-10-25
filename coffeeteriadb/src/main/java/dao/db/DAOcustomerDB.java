package dao.db;

import common.Configuration;
import common.SQLqueries;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;
import model.Customer;
import model.errors.ErrorCCustomer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//TODO: Meter el pool en todos los métodos

@Log4j2
public class DAOcustomerDB {

    private final Configuration config;
    private final DBConnection db;
    private final DBConnectionPool pool;

    @Inject
    public DAOcustomerDB(Configuration config, DBConnection db, DBConnectionPool pool) {
        this.config = config;
        this.db = db;
        this.pool = pool;
    }

    public Either<ErrorCCustomer, List<Customer>> getAll() {
        List<Customer> customerList = new ArrayList<>();
        Either<ErrorCCustomer, List<Customer>> res;
        try (Connection myConnection = db.getConnection()) {
            Statement stmt = myConnection.createStatement();
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_CUSTOMERS);
            customerList = readRS(rs);
            res = Either.right(customerList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Customer> get(int id) {
        Either<ErrorCCustomer, Customer> res;
        try (Connection myConnection = db.getConnection();
             PreparedStatement stmt = myConnection.prepareStatement(SQLqueries.SELECT_CUSTOMERS_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            List<Customer> customerList = readRS(rs);
            if (!customerList.isEmpty()) {
                res = Either.right(customerList.get(0));
            } else {
                res = Either.left(new ErrorCCustomer("Error al leer datos", 0));
            }
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    private List<Customer> readRS(ResultSet rs) throws SQLException {
        List<Customer> customerList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String first_name = rs.getString("first_name");
            String last_name = rs.getString("last_name");
            String email = rs.getString("email");
            int phone = rs.getInt("phone");
            LocalDate date = null;
            Date dateFromDB = rs.getDate("date_of_birth");
            if (dateFromDB != null) {
                date = dateFromDB.toLocalDate();
            }
            customerList.add(new Customer(id, first_name, last_name, email, phone, date));
        }
        return customerList;
    }

    public Either<ErrorCCustomer, Integer> update(Customer customer) {
        int rowsAffected;
        Either<ErrorCCustomer, Integer> res;
        try (Connection connection = db.getConnection()) {
            PreparedStatement pstmt = connection.prepareStatement(SQLqueries.UPDATE_CUSTOMERS);
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getSecondName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setInt(4, customer.getPhoneNumber());
            pstmt.setDate(5, Date.valueOf(customer.getDate()));
            pstmt.setInt(6, customer.getIdC());
            rowsAffected = pstmt.executeUpdate();
            res = Either.right(rowsAffected);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Integer> delConf(int id, boolean confirmed) {
        return delete(id, confirmed);
    }

    public Either<ErrorCCustomer, Integer> delete(int id, boolean confirmed) {
        Either<ErrorCCustomer, Integer> res;
        if (!confirmed) {
            try (Connection connection = db.getConnection()) {
                PreparedStatement pstmtOrders = connection.prepareStatement(SQLqueries.DELETE_ORDERS);
                pstmtOrders.setInt(1, id);
                pstmtOrders.executeUpdate();
                PreparedStatement pstmtCredentials = connection.prepareStatement(SQLqueries.DELETE_CREDENTIALS);
                pstmtCredentials.setInt(1, id);
                pstmtCredentials.executeUpdate();
                PreparedStatement pstmtCustomer = connection.prepareStatement(SQLqueries.DELETE_CUSTOMERS);
                pstmtCustomer.setInt(1, id);
                int rowsAffected = pstmtCustomer.executeUpdate();
                if (rowsAffected != 1) {
                    res = Either.left(new ErrorCCustomer("Error", 0));
                } else {
                    res = Either.right(rowsAffected);
                }
                res = Either.right(rowsAffected);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                if (e.getErrorCode() == 1451) {
                    res = Either.left(new ErrorCCustomer(e.getMessage(), e.getErrorCode()));
                } else {
                    res = Either.left(new ErrorCCustomer(e.getMessage(), e.getErrorCode()));
                }
            }
        } else {
            res = Either.left(new ErrorCCustomer("error", 0));
        }
        return res;
    }

    public Either<ErrorCCustomer, Integer> add(Customer customer) {
        int rowsAffected;
        Either<ErrorCCustomer, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, customer.getFirstName());
            pstmt.setString(2, customer.getSecondName());
            pstmt.setString(3, customer.getEmail());
            pstmt.setInt(4, customer.getPhoneNumber());
            if (customer.getDate() != null) {
                pstmt.setObject(5, customer.getDate());
            } else {
                pstmt.setNull(5, Types.DATE);
            }
            rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    res = Either.right(generatedId);
                } else {
                    res = Either.left(new ErrorCCustomer("No se pudo obtener el ID generado", 0));
                }
            } else {
                res = Either.left(new ErrorCCustomer("No se pudo insertar el cliente", 0));
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            res = Either.left(new ErrorCCustomer(e.getMessage(), 0));
        }
        return res;
    }
}
