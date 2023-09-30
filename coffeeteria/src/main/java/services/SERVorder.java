package services;

import dao.imp.DAOordersFICHERO;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Order;
import model.errors.ErrorCOrder;

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

    public Either<ErrorCOrder, List<Order>> getOrder(int i) {
        if (i > 0)
            //return Either.right(daOorderIMP.getOrders());
            return Either.right(daOordersFICHERO.getOrder(i).getOrNull());
        else
            return Either.left(new ErrorCOrder("Error in the Order list", 1));

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
