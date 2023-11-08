package services;

import dao.db.DAOcustomerDB;
import dao.db.DAOorderDB;
import dao.spring.DAOorderSpring;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.Order;
import model.OrderItem;
import model.errors.ErrorCCustomer;
import model.errors.ErrorCOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SERVorder {
    private final DAOcustomerDB daOcustomerDB;
    private final DAOorderDB daOorderDB;
    private final DAOorderSpring daOorderSpring;


    @Inject
    public SERVorder(DAOcustomerDB daOcustomerDB, DAOorderDB daOorderDB, DAOorderSpring daOorderSpring) {
        this.daOcustomerDB = daOcustomerDB;
        this.daOorderDB = daOorderDB;
        this.daOorderSpring = daOorderSpring;
    }

    /*Métodos*/

    public List<Order> getAll() {
        return daOorderDB.getAll().getOrNull();
        //return daOorderSpring.getAll().getOrNull();
    }

    public Either<ErrorCOrder, Order> getOrder(int i) {
        if (i > 0)
            return daOorderDB.get(i);
        else
            return Either.left(new ErrorCOrder("Error in the Order list", 1));

    }

    public Either<ErrorCOrder, Integer> add(Order order) {
        return daOorderDB.add(order);
    }

    public Either<ErrorCOrder, Integer> updateOrder(Order o) {
        return daOorderDB.update(o);
    }

    public Either<ErrorCOrder, Integer> delOrder(int i) {
       return daOorderDB.delete(i);
    }


    /*Aux*/

    public List<Order> getOrdersByDate(LocalDate selectedDate) {
        List<Order> allOrders = daOorderDB.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        if (selectedDate != null) {
            for (Order order : allOrders) {
                if (order.getOrDate() != null && order.getOrDate().isEqual(selectedDate.atStartOfDay())) {
                    filteredOrders.add(order);
                }
            }
        }
        return filteredOrders;
    }

    public List<Order> getOrdersByCustomer(int selectedCustomerId) {
        List<Order> allOrders = daOorderDB.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getIdCo() == selectedCustomerId) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

    public List<Integer> getCustomerIDs() {
        Either<ErrorCCustomer, List<Customer>> result = daOcustomerDB.getAll();
        if (result.isRight()) {
            List<Customer> customers = result.get();
            return customers.stream().map(Customer::getIdC).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
