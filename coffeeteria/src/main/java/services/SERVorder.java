package services;

import dao.imp.DAOorderIMP;
import dao.imp.DAOordersFICHERO;
import io.vavr.control.Either;
import model.Order;
import model.errors.ErrorC;

import java.util.List;

public class SERVorder {

    /*Atributos*/

    private final DAOorderIMP daOorderIMP;
    private final DAOordersFICHERO daOordersFICHERO;

    private ErrorC errorC;

    /*Constructor*/

    public SERVorder(DAOorderIMP daOorderIMP, DAOordersFICHERO daOordersFICHERO) {
        this.daOorderIMP = daOorderIMP;
        this.daOordersFICHERO = daOordersFICHERO;
    }

    /*Métodos*/

    public List<Order> getOrders() {
        //return daOorderIMP.getOrders();
        return daOordersFICHERO.getAll().getOrNull();
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
