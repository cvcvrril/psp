package dao.db;

import common.Configuration;
import common.SQLqueries;
import dao.ConstantsDAO;
import dao.DAOorder;
import dao.DBConnection;
import dao.DBConnectionPool;
import dao.modelo.Order;
import dao.modelo.OrderItem;
import domain.modelo.excepciones.BadArgumentException;
import domain.modelo.excepciones.BaseCaidaException;
import domain.modelo.excepciones.WrongObjectException;
import domain.servicios.SERVorderItem;
import io.vavr.control.Either;
import jakarta.excepciones.ApiError;
import jakarta.inject.Inject;
import lombok.extern.log4j.Log4j2;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**Los doble try están puestos por el AutoCommit**/

@Log4j2
public class DAOorderDB implements DAOorder {

    private final DBConnection db;
    private final DBConnectionPool pool;
    private final SERVorderItem serv;

    @Inject
    public DAOorderDB(Configuration config, DBConnection db, DBConnectionPool pool, SERVorderItem serv) {
        this.db = db;
        this.pool = pool;
        this.serv = serv;
    }

    public Either<ApiError, List<Order>> getAll() {
        List<Order> orderList = new ArrayList<>();
        Either<ApiError, List<Order>> res;
        try (Connection myConnection = pool.getConnection()) {
            Statement stmt = myConnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(SQLqueries.SELECT_FROM_ORDERS);
            orderList = readRS(rs);
            res = Either.right(orderList);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, Order> get(int id) {
        Either<ApiError, Order> res;
        try (Connection myConnection = pool.getConnection()) {
            PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.SELECT_ORDERS_ID);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            List<Order> orderList = readRS(rs);
            if (!orderList.isEmpty()) {
                res = Either.right(orderList.get(0));
            } else {
                throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
            }
            rs.close();
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }


    public Either<ApiError, Integer> update(Order order) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = pool.getConnection()) {
            try (PreparedStatement pstmt = myConnection.prepareStatement(SQLqueries.UPDATE_ORDERS)) {
                myConnection.setAutoCommit(false);
                pstmt.setTimestamp(1, Timestamp.valueOf(order.getOrDate()));
                pstmt.setInt(2, order.getIdCo());
                pstmt.setInt(3, order.getIdTable());
                pstmt.setInt(4, order.getIdOrd());
                rowsAffected = pstmt.executeUpdate();
                myConnection.commit();
                res = Either.right(rowsAffected);
            } catch (SQLException e) {
                log.error(e.getMessage(), e);
                throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, Integer> delete(int id) {
        Either<ApiError, Integer> res;
        try (Connection myConnection = db.getConnection()) {
            PreparedStatement pstmt1 = myConnection.prepareStatement(SQLqueries.DELETE_ORDER_ITEMS);
            pstmt1.setInt(1, id);
            pstmt1.executeUpdate();
            PreparedStatement pstmt2 = myConnection.prepareStatement(SQLqueries.DELETE_ORDERS_ID);
            pstmt2.setInt(1, id);
            int rowsAffected = pstmt2.executeUpdate();
            if (rowsAffected != 1) {
                throw new WrongObjectException(ConstantsDAO.WRONG_OBJECT_EXCEPTION);
            } else {
                res = Either.right(rowsAffected);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }
        return res;
    }

    public Either<ApiError, Integer> add(Order order) {
        int rowsAffected;
        Either<ApiError, Integer> res;
        try (Connection myConnection = pool.getConnection()) {
            myConnection.setAutoCommit(false);

            //Lo del order
            try (PreparedStatement pstmtOrder = myConnection.prepareStatement(SQLqueries.INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
                pstmtOrder.setTimestamp(1, Timestamp.valueOf(order.getOrDate()));
                pstmtOrder.setInt(2, order.getIdCo());
                pstmtOrder.setInt(3, order.getIdTable());
                rowsAffected = pstmtOrder.executeUpdate();
                ResultSet rs = pstmtOrder.getGeneratedKeys();
                if (rs.next()) {
                    order.setIdOrd(rs.getInt(1));
                }

                if (rowsAffected != 1) {
                    myConnection.rollback();
                    throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
                } else {
                    //Lo de los orderItems
                    for (OrderItem orderItem : order.getOrderItems()) {
                        try (PreparedStatement pstmtOrderItem = myConnection.prepareStatement(SQLqueries.INSERT_ORDER_ITEM)) {
                            pstmtOrderItem.setInt(1, order.getOrderItems().get(0).getOrderId());
                            pstmtOrderItem.setInt(2, order.getIdOrd());
                            pstmtOrderItem.setInt(3, orderItem.getMenuItem());
                            pstmtOrderItem.setInt(4, orderItem.getQuantity());
                            pstmtOrderItem.executeUpdate();
                        }
                    }
                    myConnection.commit();
                    res = Either.right(rowsAffected);
                }
            } catch (SQLException e) {
                myConnection.rollback();
                log.error(e.getMessage(), e);
                throw new BadArgumentException(ConstantsDAO.BAD_ARGUMENT_EXCEPTION);
            } finally {
                myConnection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            throw new BaseCaidaException(ConstantsDAO.BASE_CAIDA_EXCEPTION);
        }

        return res;
    }

    private List<Order> readRS(ResultSet rs) throws SQLException {
        List<Order> orderList = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt(ConstantsDAO.ORDER_ID);
            LocalDateTime dateTime = null;
            Timestamp timestamp = rs.getTimestamp(ConstantsDAO.ORDER_DATE);
            if (timestamp != null) {
                dateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            }
            int customerId = rs.getInt(ConstantsDAO.CUSTOMER_ID);
            int tableId = rs.getInt(ConstantsDAO.TABLE_ID);
            orderList.add(new Order(id, dateTime, customerId, tableId, serv.getOrders(id).get()));
        }
        return orderList;
    }

}
