package dao.imp;

import dao.DAOorder;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorCOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DAOorderIMP implements DAOorder {

    public List<Order> orderList(){
        List<Order> resOrderList = new ArrayList<>();
        resOrderList.add(new Order(1, 1, 1, LocalDate.now()));
        resOrderList.add(new Order(2, 2, 1, LocalDate.now()));
        resOrderList.add(new Order(3, 2, 3, LocalDate.now()));
        return resOrderList;


    }

//    private static List<Order> orders;
//
//    static {
//        orders = new ArrayList<>();
//        orders.add(new Order(1, 1, 1, LocalDate.now()));
//        orders.add(new Order(2, 2, 1, LocalDate.now()));
//        orders.add(new Order(3, 2, 3, LocalDate.now()));
//    }

    public List<Order> getOrders() {
        return orderList();
        //return orders;
    }

    @Override
    public Either<ErrorCOrder, Order> getOrder(int id) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, Integer> save(Order t) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, Integer> update(Order t) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, Integer> delete(Order t) {
        return null;
    }

    @Override
    public Either<ErrorCOrder, List<Order>> getAll() {
        return null;
    }

}
