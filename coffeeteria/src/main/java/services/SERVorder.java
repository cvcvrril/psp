package services;

import dao.imp.DAOclientsFILE;
import dao.imp.DAOorderFILE;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Customer;
import model.Order;
import model.errors.ErrorCCustomer;
import model.errors.ErrorCOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SERVorder {

    /*Atributos*/

    private final DAOorderFILE daOorderFILE;
    private final DAOclientsFILE daOclientsFILE;

    /*Constructor*/

    @Inject
    public SERVorder(DAOorderFILE daOorderFILE, DAOclientsFILE daOclientsFILE) {
        this.daOorderFILE = daOorderFILE;
        this.daOclientsFILE = daOclientsFILE;
    }

    /*Métodos*/

    public List<Order> getAll() {
        return daOorderFILE.getAll().getOrNull();
    }

    public Either<ErrorCOrder, Order> getOrder(int i) {
        if (i > 0)
            return daOorderFILE.get(i);
        else
            return Either.left(new ErrorCOrder("Error in the Order list", 1));

    }

    public Either<ErrorCOrder, Integer> saveOrder(Order o) {
        return daOorderFILE.save(o);
    }

    public Either<ErrorCOrder, Integer> updateOrder(Order o) {
        return daOorderFILE.update(o);
    }

    public Either<ErrorCOrder, Integer> delOrder(Order o) {
       return daOorderFILE.delete(o);
    }


    /*Aux*/

    public List<Order> getOrdersByDate(LocalDate selectedDate) {
        List<Order> allOrders = daOorderFILE.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        if (selectedDate != null) {
            for (Order order : allOrders) {
                if (order.getOrDate() != null && order.getOrDate().isEqual(selectedDate)) {
                    filteredOrders.add(order);
                }
            }
        }
        return filteredOrders;
    }

    public List<Order> getOrdersByCustomer(int selectedCustomerId) {
        List<Order> allOrders = daOorderFILE.getAll().getOrNull();
        List<Order> filteredOrders = new ArrayList<>();
        for (Order order : allOrders) {
            if (order.getIdCo() == selectedCustomerId) {
                filteredOrders.add(order);
            }
        }
        return filteredOrders;
    }

    public List<Integer> getCustomerIDs() {
        Either<ErrorCCustomer, List<Customer>> result = daOclientsFILE.getAll();
        if (result.isRight()) {
            List<Customer> customers = result.get();
            return customers.stream().map(Customer::getIdC).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
