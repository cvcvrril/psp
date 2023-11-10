package domain.servicios;

import dao.db.DAOorderItemDB;
import dao.modelo.OrderItem;
import io.vavr.control.Either;
import jakarta.inject.Inject;

import java.util.List;

public class SERVorderItem {
    private final DAOorderItemDB daOorderItemDB;

    @Inject
    public SERVorderItem(DAOorderItemDB daOorderItemDB) {
        this.daOorderItemDB = daOorderItemDB;
    }

    public Either<Exception, List<OrderItem>> getOrders(int i) {
        return daOorderItemDB.getByOrderId(i);
    }

    public Either<Exception, List<OrderItem>> getAll(){
        return daOorderItemDB.getAll();
    }

    public Either<Exception, Integer> updateOrders(OrderItem updatedOrderItm) {
        return daOorderItemDB.update(updatedOrderItm);
    }


}
