package services;

import dao.imp.DAOorderIMP;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public class SERVorder {

    /*Atributos*/

    private DAOorderIMP daOorderIMP;

    private ErrorC errorC;

    /*Constructor*/

    public SERVorder(DAOorderIMP daOorderIMP) {
        this.daOorderIMP = daOorderIMP;
    }

    /*Métodos*/

    public List<Order> getOrders() {
        return daOorderIMP.getOrders();
    }

    public Either<ErrorC, List<Order>> getOrders(int i) {
        if (i > 0)
            return Either.right(daOorderIMP.getOrders());
        else
            return Either.left(errorC);

    }

    public Order saveOrder(int i) {
        return null;
    }

    public Order updateOrder(int i) {
        return null;
    }

    public Order delOrder(int i) {
        return null;
    }


}
