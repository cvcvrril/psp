package services;

import dao.imp.DAOordersFICHERO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.ErrorCOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SERVorder {

    /*Atributos*/

    //private final DAOorderIMP daOorderIMP;
    private final DAOordersFICHERO daOordersFICHERO;

    /*Constructor*/

    @Inject
    public SERVorder(DAOordersFICHERO daOordersFICHERO) {
        //this.daOorderIMP = daOorderIMP;
        this.daOordersFICHERO = daOordersFICHERO;
    }

    /*Métodos*/

    public List<Order> getAll() {
        //return daOorderIMP.getOrders();
        return daOordersFICHERO.getAll().getOrNull();
    }

    public Either<ErrorCOrder, Order> getOrder(int i) {
        if (i > 0)
            //return Either.right(daOorderIMP.getOrders());
            return daOordersFICHERO.getOrder(i);
        else
            return Either.left(new ErrorCOrder("Error in the Order list", 1));

    }

    public Either<ErrorCOrder, Integer> saveOrder(Order o) {
        return daOordersFICHERO.save(o);
    }

    public Either<ErrorCOrder, Integer> updateOrder(Order o) {
        return daOordersFICHERO.update(o);
    }

    public Either<ErrorCOrder, Integer> delOrder(Order o) {
        return daOordersFICHERO.delete(o);
    }


    /*Aux*/

    public List<Order> getOrdersByDate(LocalDate selectedDate) {
        List<Order> allOrders = daOordersFICHERO.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getOr_date().isEqual(selectedDate)) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

    public List<Order> getOrdersByCustomer(int selectedCustomerId) {
        List<Order> allOrders = daOordersFICHERO.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getId_co() == selectedCustomerId) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

}
