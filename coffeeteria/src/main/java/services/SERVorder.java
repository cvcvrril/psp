package services;

import dao.imp.DAOclientsFICHERO;
import dao.imp.DAOordersFICHERO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Client;
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

    //private final DAOorderIMP daOorderIMP;
    private final DAOordersFICHERO daOordersFICHERO;
    private final DAOclientsFICHERO daOclientsFICHERO;

    /*Constructor*/

    @Inject
    public SERVorder(DAOordersFICHERO daOordersFICHERO, DAOclientsFICHERO daOclientsFICHERO) {
        //this.daOorderIMP = daOorderIMP;
        this.daOordersFICHERO = daOordersFICHERO;
        this.daOclientsFICHERO = daOclientsFICHERO;
    }

    /*Métodos*/

    public List<Order> getAll() {
        return daOordersFICHERO.getAll().getOrNull();
    }

    public Either<ErrorCOrder, Order> getOrder(int i) {
        if (i > 0)
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

    public List<Integer> getCustomerIDs() {
        Either<ErrorCCustomer, List<Client>> result = daOclientsFICHERO.getAll();
        if (result.isRight()) {
            List<Client> clients = result.get();
            return clients.stream().map(Client::getId_c).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

}
